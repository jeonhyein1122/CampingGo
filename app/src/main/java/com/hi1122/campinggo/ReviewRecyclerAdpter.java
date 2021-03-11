package com.hi1122.campinggo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReviewRecyclerAdpter extends RecyclerView.Adapter {

    Context context;
    ArrayList<ReviewRecyclerItem> items;

    public ReviewRecyclerAdpter(Context context, ArrayList<ReviewRecyclerItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.review_recycler_item,parent,false);
        VH vh=new VH(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh=(VH)holder;
        //값 연결작업..
        ReviewRecyclerItem item=items.get(position);

        ((VH) holder).tvTitle.setText(item.title);
        ((VH) holder).tvdate.setText("등록일 : "+item.loaddate);
        ((VH) holder).tvdetail.setText(item.detail);

        String imgUrl="http://jhyein1122.dothome.co.kr/Campinggoreview/"+item.file;
        Log.i("tagread",imgUrl);

        Glide.with(context).load(imgUrl).into(((VH) holder).iv);


        if (item.favor==0) ((VH) holder).tbFavor.setChecked(false);
        else  ((VH) holder).tbFavor.setChecked(true);

    }

   @Override
    public int getItemCount() {
        return items.size();
    }

    class  VH extends RecyclerView.ViewHolder{

//        item.xml 안에 있는거 집어넣기
        ImageView iv;
        TextView tvTitle;
        TextView tvdetail;
        TextView tvdate;
        ToggleButton tbFavor;


        public VH(@NonNull View itemView) {
            super(itemView);

            iv=itemView.findViewById(R.id.review_iv);
            tvTitle=itemView.findViewById(R.id.review_title);
            tvdetail=itemView.findViewById(R.id.review_detail);
            tvdate=itemView.findViewById(R.id.review_date);
            tbFavor=itemView.findViewById(R.id.tb_favor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getLayoutPosition();


                    String reviewiv=items.get(position).file;
                    String title=items.get(position).title;
                    String loaddate=items.get(position).loaddate;
                    String detail=items.get(position).detail;

                    Intent intent=new Intent(context,Review_DetailActivity.class);
                    intent.putExtra("reviewiv",reviewiv);
                    intent.putExtra("title",title);
                    intent.putExtra("loaddate",loaddate);
                    intent.putExtra("detail",detail);

                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity)context,new Pair<View,String>(iv,"reviewimg"));
                        context.startActivity(intent,options.toBundle());
                    }else {
                        context.startActivity(intent);
                    }

                }
            });


            //좋아요 서버 체크
            tbFavor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position=getLayoutPosition();
                    ReviewRecyclerItem item=items.get(position);
                    item.favor=isChecked? 1:0; //변수 값 변경

                    //db update
                    Retrofit retrofit=RetrofitHelper.getRetrofitInstanceGson();
                    RetrofitServiceReview retrofitServicereview=retrofit.create(RetrofitServiceReview.class);
                    Call<ReviewRecyclerItem> call= retrofitServicereview.updateData("updateFavor.php", item);
                    call.enqueue(new Callback<ReviewRecyclerItem>() {
                        @Override
                        public void onResponse(Call<ReviewRecyclerItem> call, Response<ReviewRecyclerItem> response) {
                            Toast.makeText(context, "좋아요 저장완료", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ReviewRecyclerItem> call, Throwable t) {
//                            Toast.makeText(context, "error:Review Ad--"+t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            });
        }
    }
}
