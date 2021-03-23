package com.hi1122.campinggo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class Mypage_Setting_MyFCMService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=null;
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("ch1","push ch",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);

            builder=new NotificationCompat.Builder(this,"ch1");
        }else{
            builder=new NotificationCompat.Builder(this,null);

        }

        //파라미터 remoteMessage: FCM으로부터 전달받은 원격메세지
        String fromWho =remoteMessage.getFrom(); // 메세지를 보낸사람 기기명[firebase

        //알림에 넣을 데이터
        String notiTitle="title";   //원격메세지에 알림제목정보가 없을때의 기본값
        String notiText="message";  //원격메세지에 알림메세지정보가 없을때의 기본값

        if(remoteMessage.getNotification() !=null){
            notiTitle=remoteMessage.getNotification().getTitle();
            notiText=remoteMessage.getNotification().getBody();

        }

        //알림의 설정들
        builder.setSmallIcon(R.drawable.chatting);
        builder.setContentTitle(notiTitle);
        builder.setContentText(notiText);
        builder.setAutoCancel(true);

        //firebase 푸시 메세지에 알림(Notification)외에 추가로 보내는 data가
        //있을때 이 값들을 [키:벨류]으로 전달되어옴
        Map<String,String> data=remoteMessage.getData();
        if(data!=null){
            //전달 된 데이터에서 글씨 얻어오기
            String name=data.get("name");
            String message=data.get("msg");

            //알림창을 선택했을때 실행될 액티비티 정보를 가진 intent
            Intent intent=new Intent(this,Mypage_Setting_Message.class);

            intent.putExtra("name",name);
            intent.putExtra("msg",message);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            //바로 실행되지 않고 알림에 보관되어 있다가 실행되야 하므로 보류중인 인텐트로 변경
            PendingIntent pendingIntent=PendingIntent.getActivity(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

        }


        //알림매니저를 통해 알림 공지
        notificationManager.notify(1122,builder.build());
    }
}

