package com.hi1122.campinggo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class ChattingList extends AppCompatActivity {

    RecyclerView recyclerView;
    Chatting_RecyclerAdapter recyclerAdapter;
    ArrayList<Integer>serverNum;
    ArrayList<Chatting_MessageItem> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_list);

//        item.add(new Chatting_MessageItem("hi","nice","33",null));
//        item.add(new Chatting_MessageItem("hi","nice","33",null));

        recyclerView=findViewById(R.id.recycler);
        items = new ArrayList<>();
        serverNum = new ArrayList<>();
        recyclerAdapter = new Chatting_RecyclerAdapter(this, items, serverNum);
        recyclerView.setAdapter(recyclerAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
//        loadChattingData();
    }

//    public void loadChattingData(){
//        items.clear();
//        serverNum.clear();
//        FirebaseDatabase.getInstance().getReference("chat").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
//            @Override
//            public void onSuccess(DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()){
//                    String[] chatNames = ds.getKey().split("&&");
//                    ArrayList<String> chatNameArray = new ArrayList<>(Arrays.asList(chatNames));
//                    int index = chatNameArray.indexOf(G.nickname);
//                    if (index == 0){
//                        items.add(chatNameArray.get(1));
//                        serverNum.add(1);
//                    }else if (index == 1){
//                        items.add(chatNameArray.get(0));
//                        serverNum.add(0);
//                    }
//                }
//                recyclerAdapter.notifyDataSetChanged();
//            }
//        });
//    }
}