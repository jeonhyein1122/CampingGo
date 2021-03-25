package com.hi1122.campinggo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hi1122.campinggo.Chatting_MessageItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chatting_RecyclerAdapter extends RecyclerView.Adapter{

    Context context;

    ArrayList<Integer> serverNum;
    ArrayList<Chatting_MessageItem> items;



    public Chatting_RecyclerAdapter(ChattingList context, ArrayList<Chatting_MessageItem> items, ArrayList<Integer> serverNum) {
        this.context = context;
        this.items = items;
        this.serverNum = serverNum;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.chatting_list_item,parent,false);
        VH holder=new VH(itemView);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh=(VH)holder;

        Chatting_MessageItem item=items.get(position);

//        Intent intent=getIntent();
//        String detailnickname=intent.getStringExtra("nickname");


//        ((VH) holder).othernick.setText(item.name);
        ( (VH)  holder).othernick.setText(item.name+"님과의 채팅방");
        ( (VH)  holder).chatdate.setText(item.time+"에 채팅이 왔습니다.");



        //상대방 사진
        Glide.with(context).load(item.profileUrl).into(((VH) holder).chativ);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class  VH extends RecyclerView.ViewHolder {

        CircleImageView chativ;
        TextView othernick;
        TextView chatdate;



        public VH(@NonNull View itemView) {
            super(itemView);
            chativ=itemView.findViewById(R.id.chatprofile);
            othernick=itemView.findViewById(R.id.chatothernic);
            chatdate=itemView.findViewById(R.id.chatdate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,Chatting_Activity.class);
                    int position=getLayoutPosition();
                    if (serverNum.get(position)==1){
                        intent.putExtra("server", G.nickname + "&&" + items.get(position));
                    } else if (serverNum.get(position)==0){
                        intent.putExtra("server", items.get(position) + "&&" +G.nickname );
                    }
                    context.startActivity(intent);
                }
            });

        }
    }
}