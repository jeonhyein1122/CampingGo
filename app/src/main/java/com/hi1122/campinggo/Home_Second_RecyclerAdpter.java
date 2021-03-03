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

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_Second_RecyclerAdpter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Home_Second_RecyclerItem> items;

    public Home_Second_RecyclerAdpter(Context context, ArrayList<Home_Second_RecyclerItem> items) {
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

        Home_Second_RecyclerItem item=items.get(position);
//        vh.title.setText(item.title);
//        vh.detial.setText(item.detail);
//
//        Glide.with(context).load(item.img).into(vh.img);
//        Glide.with(context).load(item.detailimg)

    }

   @Override
    public int getItemCount() {
        return items.size();
    }

    class  VH extends RecyclerView.ViewHolder{

//        item.xml 안에 있는거 집어넣기
        CircleImageView img;
        TextView title;
        TextView detial;


        public VH(@NonNull View itemView) {
            super(itemView);

//            img=itemView.findViewById(R.id.img);
        }
    }
}
