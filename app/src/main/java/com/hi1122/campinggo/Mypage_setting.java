package com.hi1122.campinggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kyleduo.switchbutton.SwitchButton;

public class Mypage_setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_setting);

        Switch switchButton=findViewById(R.id.switchbutton);

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Mypage_setting.this, "앱 등록실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            String token=task.getResult();
                            //추후 이 토큰값을 dothom 서버에서 사용하고자 하기에 LOG로 출력해보기
                            //그리고 화면에 보기 위해 Toast도 출력
                            Toast.makeText(Mypage_setting.this, ""+token, Toast.LENGTH_SHORT).show();
                            Log.i("TOKEN",token);

                            //원래는 이 token값을 웹서버(dothome 같은)에 전송하여
                            //회원정보를 DB에 저장하듯이 token 값도 DB에 저장해 놓아야함

                        }
                    });
                }
            }
        });
    }

//    public void clickBtn(View view) {
//        //FCM서버에서 디바이스 고유 등록 id(토큰) 얻어오기
//
//        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
//            @Override
//            public void onComplete(@NonNull Task<String> task) {
//                if(!task.isSuccessful()){
//                    Toast.makeText(Mypage_setting.this, "앱 등록실패", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                String token=task.getResult();
//                //추후 이 토큰값을 dothom 서버에서 사용하고자 하기에 LOG로 출력해보기
//                //그리고 화면에 보기 위해 Toast도 출력
//                Toast.makeText(Mypage_setting.this, ""+token, Toast.LENGTH_SHORT).show();
//                Log.i("TOKEN",token);
//
//                //원래는 이 token값을 웹서버(dothome 같은)에 전송하여
//                //회원정보를 DB에 저장하듯이 token 값도 DB에 저장해 놓아야함
//
//            }
//        });
//    }
//
//    public void clickBtn2(View view) {
//        //특정 주제를 구독하는 버튼. 이를 구독한 사람들만 메세지를 선별하여 받아짐
//        FirebaseMessaging.getInstance().subscribeToTopic("movie").addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Toast.makeText(Mypage_setting.this, "영화를 구독하셨습니다.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}