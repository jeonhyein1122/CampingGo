package com.hi1122.campinggo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ChattingList extends AppCompatActivity {

    RecyclerView recyclerView;
    Chatting_RecyclerAdapter recyclerAdpter;
    ArrayList<Chatting_MessageItem> item= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_list);

        item.add(new Chatting_MessageItem("hi","nice","33",null));
        item.add(new Chatting_MessageItem("hi","nice","33",null));

        recyclerView=findViewById(R.id.recycler);
        recyclerAdpter=new Chatting_RecyclerAdapter(ChattingList.this,item);
        recyclerView.setAdapter(recyclerAdpter);


    }
}