package com.hi1122.campinggo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Home_First_Fragment extends Fragment {

    ArrayList<Home_First_RecyclerItem> items=new ArrayList<>();
    RecyclerView recyclerView;
    Home_First_RecyclerAdpter recyclerAdpter;
    Button Btnall;

    ArrayList<Home_First_RecyclerItem> items2=new ArrayList<>();
    RecyclerView recyclerView2;
    Home_First_RecyclerAdpter recyclerAdpter2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.home_tab_first,container,false);

        //open api 전체 버튼
        Btnall=view.findViewById(R.id.btnall);
        Btnall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Home_First_Tab_Btnall.class); //전체 버튼에 들어갈 클래스 만들기
                startActivity(intent);

            }
        });
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.recycler);
        recyclerAdpter=new Home_First_RecyclerAdpter(getActivity(),items);
        recyclerView.setAdapter(recyclerAdpter);

        recyclerView2=view.findViewById(R.id.recycler2);
        recyclerAdpter2=new Home_First_RecyclerAdpter(getActivity(),items2);
        recyclerView2.setAdapter(recyclerAdpter2);
    }

    void loadData(){

        items.add(new Home_First_RecyclerItem());
        items.add(new Home_First_RecyclerItem());
        items.add(new Home_First_RecyclerItem());
        items.add(new Home_First_RecyclerItem());
        items.add(new Home_First_RecyclerItem());
        items.add(new Home_First_RecyclerItem());


        items2.add(new Home_First_RecyclerItem());
        items2.add(new Home_First_RecyclerItem());
        items2.add(new Home_First_RecyclerItem());
        items2.add(new Home_First_RecyclerItem());
        items2.add(new Home_First_RecyclerItem());
        items2.add(new Home_First_RecyclerItem());
    }
}
