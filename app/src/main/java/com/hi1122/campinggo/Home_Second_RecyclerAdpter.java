package com.hi1122.campinggo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
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
        VH holder=new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh=(VH)holder;

        Home_Second_RecyclerItem item=items.get(position);

        ((VH) holder).Title.setText(item.title);
        ((VH) holder).Subtitle.setText("#"+item.subtitle);


        String imgUrl="http://jhyein1122.dothome.co.kr/Campinggotip/"+item.file;
//        Log.i("tagshad",imgUrl);

        Glide.with(context).load(imgUrl).into(((VH) holder).iv);


    }

   @Override
    public int getItemCount() {
        return items.size();
    }

    class  VH extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView Title;
        TextView Subtitle;


        public VH(@NonNull View itemView) {
            super(itemView);


            iv=itemView.findViewById(R.id.homesecond_img);
            Title=itemView.findViewById(R.id.homesecond_title);
            Subtitle=itemView.findViewById(R.id.homesecond_subtitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getLayoutPosition();
//                    Toast.makeText(context, "클릭됨", Toast.LENGTH_SHORT).show();

                    String tipiv=items.get(position).file;
                    String title=items.get(position).title;
                    String subtitle=items.get(position).subtitle;
                    String detail=items.get(position).detail;

                    Intent intent=new Intent(context,Home_Second_Detail.class);
                    intent.putExtra("tipiv",tipiv);
//                    Log.i("tag1",shoppingiv+"");
                    intent.putExtra("title",title);
                    intent.putExtra("subtitle",subtitle);
                    intent.putExtra("detail",detail);

                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity)context,new Pair<View,String>(iv,"tipimg"));
                        context.startActivity(intent,options.toBundle());
                    }else {
                        context.startActivity(intent);
                    }

                }
            });


        }
    }
}
