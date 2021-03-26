package com.hi1122.campinggo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Chatting_Activity extends AppCompatActivity {
    ListView listView;
    EditText et_chat;

    ArrayList<Chatting_MessageItem> items;
    ChattingAdapter adapter;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_activity);
        listView = findViewById(R.id.listview);
        et_chat = findViewById(R.id.et);

        items = new ArrayList<>();
        adapter = new ChattingAdapter(this, items);
        listView.setAdapter(adapter);

//        Toast.makeText(this, G.othernickname+"&&"+G.userID, Toast.LENGTH_SHORT).show();


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("chat").child(G.othernickname+"&&"+G.nickname);
        if (getIntent().getStringExtra("server") != null){
            databaseReference = firebaseDatabase.getReference("chat").child(getIntent().getStringExtra("server"));

        }
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Chatting_MessageItem item = snapshot.getValue(Chatting_MessageItem.class);
                items.add(item);
                adapter.notifyDataSetChanged();
                listView.setSelection(items.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void clickSend(View view) {
        String message = et_chat.getText().toString(); //data
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); //child
        String visualTime = new SimpleDateFormat("HH : mm").format(new Date()); //time

        if (message.equals("")) return;

//        G.userID, message, visualTime

        databaseReference.child(time).setValue(new Chatting_MessageItem(G.nickname,message,visualTime,G.profile)).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                et_chat.setText("");



            }
        });

    }
}