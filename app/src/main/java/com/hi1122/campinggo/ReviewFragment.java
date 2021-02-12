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

public class ReviewFragment extends Fragment {

    ArrayList<ReviewRecyclerItem> items=new ArrayList<>();
    RecyclerView recyclerView;
    ReviewRecyclerAdpter recyclerAdpter;
    Button btnadd;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_review,container,false);

       btnadd=view.findViewById(R.id.addreview);

       btnadd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(getActivity(),Review_add_Activity.class);

               startActivity(intent);

           }
       });

       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.recycler);
        recyclerAdpter=new ReviewRecyclerAdpter(getActivity(),items);
        recyclerView.setAdapter(recyclerAdpter);
    }

    void loadData(){

        items.add(new ReviewRecyclerItem());
        items.add(new ReviewRecyclerItem());
        items.add(new ReviewRecyclerItem());
        items.add(new ReviewRecyclerItem());
        items.add(new ReviewRecyclerItem());
        items.add(new ReviewRecyclerItem());
        items.add(new ReviewRecyclerItem());
        items.add(new ReviewRecyclerItem());
        items.add(new ReviewRecyclerItem());


    }


}
