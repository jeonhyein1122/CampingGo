package com.hi1122.campinggo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Home_First_RecyclerAdpter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Home_First_RecyclerItem> items;

    public Home_First_RecyclerAdpter(Context context, ArrayList<Home_First_RecyclerItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.home_recycler_item_second,parent,false);
        VH vh=new VH(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh=(VH)holder;
        //값 연결작업..
        Home_First_RecyclerItem item=items.get(position);
        //나머지는 알아서서
    }

   @Override
    public int getItemCount() {
        return items.size();
    }

    class  VH extends RecyclerView.ViewHolder{

//        item.xml 안에 있는거 집어넣기
        ImageView iv;
        TextView title;
        TextView msg;
        RatingBar ratingBar; //별점


        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
