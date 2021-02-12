package com.hi1122.campinggo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Home_Second_Fragment extends Fragment {

    ArrayList<Home_Second_RecyclerItem> items=new ArrayList<>();
    RecyclerView recyclerView;
    Home_Second_RecyclerAdpter recyclerAdpter;

    ArrayList<Home_Second_RecyclerItem> items2=new ArrayList<>();
    RecyclerView recyclerView2;
    Home_Second_RecyclerAdpter recyclerAdpter2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_tab_second,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.recycler);
        recyclerAdpter=new Home_Second_RecyclerAdpter(getActivity(),items);
        recyclerView.setAdapter(recyclerAdpter);

        recyclerView2=view.findViewById(R.id.recycler2);
        recyclerAdpter2=new Home_Second_RecyclerAdpter(getActivity(),items2);
        recyclerView2.setAdapter(recyclerAdpter2);
    }

    void loadData(){

        items.add(new Home_Second_RecyclerItem());
        items.add(new Home_Second_RecyclerItem());
        items.add(new Home_Second_RecyclerItem());
        items.add(new Home_Second_RecyclerItem());
        items.add(new Home_Second_RecyclerItem());
        items.add(new Home_Second_RecyclerItem());



        items2.add(new Home_Second_RecyclerItem());
        items2.add(new Home_Second_RecyclerItem());
        items2.add(new Home_Second_RecyclerItem());
        items2.add(new Home_Second_RecyclerItem());
        items2.add(new Home_Second_RecyclerItem());
        items2.add(new Home_Second_RecyclerItem());
        items2.add(new Home_Second_RecyclerItem());

    }
}
