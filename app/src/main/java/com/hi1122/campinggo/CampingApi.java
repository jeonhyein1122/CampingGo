package com.hi1122.campinggo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CampingApi extends AppCompatActivity {

    ArrayList<CampingApiRecyclerItem> items=new ArrayList<>();
    RecyclerView recyclerView;
    CampingApiAdapter adapter;

//    String apiKey="fPVCaLqlpYv1liNHjyn0aYOBfnyVvPtLiyXQO%2BHXpfaNP2SHBHzZjjZ8SQgYsxtxiVti1V6j4YmjHkf8O4mzQw%3D%3D";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camping_a_p_i);

        items.add(new CampingApiRecyclerItem("왕십리 캠핑장","우와 신기해",R.drawable.test));
        items.add(new CampingApiRecyclerItem("성수 캠핑장","우와 신기해",R.drawable.test));
        items.add(new CampingApiRecyclerItem("춘의역 캠핑장","우와 신기해",R.drawable.test));
        items.add(new CampingApiRecyclerItem("광주광역시 캠핑장","우와 신기해",R.drawable.test));
        items.add(new CampingApiRecyclerItem("상왕십리 캠핑장","우와 신기해",R.drawable.test));

        recyclerView=findViewById(R.id.recyclercampingapi);
        adapter=new CampingApiAdapter(this,items);
        recyclerView.setAdapter(adapter);
//
//        Thread t=new Thread(){
//            @Override
//            public void run() {
//
//
//
//            }
//        };

    }
}