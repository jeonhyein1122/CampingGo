package com.hi1122.campinggo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
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

public class CampingApi_General_Adapter extends RecyclerView.Adapter{
    Context context;
    ArrayList<CampingApi_General_RecyclerItem> items;
    ProgressDialog progressDialog;

    public CampingApi_General_Adapter(Context context, ArrayList<CampingApi_General_RecyclerItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);

        View itemView=inflater.inflate(R.layout.camping_api_recycler_item,parent,false);
        VH holder=new VH(itemView);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh=(VH)holder;

        CampingApi_General_RecyclerItem item=items.get(position);
        vh.name.setText(item.name+"");
        if (item.lineintro !=null) vh.lineintro.setText(item.lineintro+"");
        else vh.lineintro.setText(" ");

        if(item.campingimg!=null){
//            vh.campingimg.setVisibility(View.VISIBLE);
//            vh.itemView.setVisibility(View.VISIBLE);
            Glide.with(context).load(item.campingimg).into(vh.campingimg);
        }
        else  Glide.with(context).load(R.drawable.noimage).into(vh.campingimg);
//       else vh.campingimg.setVisibility(View.GONE);
//        else vh.itemView.setVisibility(View.GONE);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        ImageView campingimg;
        TextView name;
        TextView lineintro;
        ToggleButton tbFavor;
        ToggleButton tbRecommend;


        public VH(@NonNull View itemView) {
            super(itemView);

            campingimg=itemView.findViewById(R.id.campingimg);
            name=itemView.findViewById(R.id.name);
            lineintro=itemView.findViewById(R.id.lineintro);
            tbFavor=itemView.findViewById(R.id.tb_favor);
            tbRecommend=itemView.findViewById(R.id.tb_recommend);

            //아이템뷰 클릭
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getLayoutPosition();
//                    Toast.makeText(context, "클릭됨", Toast.LENGTH_SHORT).show();

                    String nameId=items.get(position).name;
                    String lineintroId=items.get(position).lineintro;
                    String campingimgId=items.get(position).campingimg;
                    String introId=items.get(position).intro;
                    String indutyId=items.get(position).induty;
                    String addr1Id=items.get(position).addr1;
                    String addr2Id=items.get(position).addr2;
                    String resveClId=items.get(position).resveCl;
                    String createdtimeId=items.get(position).createdtime;
                    String modifiedtimeId=items.get(position).modifiedtime;
                    String telId=items.get(position).tel;
                    String homepageId=items.get(position).homepage;
                    String resveUrlId=items.get(position).resveUrl;
                    String mapX=items.get(position).mapX;
                    String mapY=items.get(position).mapY;
                    String lctCl=items.get(position).lctCl;

                    Intent intent=new Intent(context,CampingApi_DetailActivity.class);
                    intent.putExtra("nameId",nameId);
                    intent.putExtra("lineintroId",lineintroId);
                    intent.putExtra("campingimgId",campingimgId);
                    intent.putExtra("introId",introId);
                    intent.putExtra("indutyId",indutyId);
                    intent.putExtra("addr1Id",addr1Id);
                    intent.putExtra("addr2Id",addr2Id);
                    intent.putExtra("resveClId",resveClId);
                    intent.putExtra("createdtimeId",createdtimeId);
                    intent.putExtra("modifiedtimeId",modifiedtimeId);
                    intent.putExtra("telId",telId);
                    intent.putExtra("homepageId",homepageId);
                    intent.putExtra("resveUrlId",resveUrlId);
                    intent.putExtra("mapX",mapX);
                    intent.putExtra("mapY",mapY);
                    intent.putExtra("lctCl",lctCl);



                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity)context,new Pair<View,String>(campingimg,"Campingimg"));
                        context.startActivity(intent,options.toBundle());
                    }else {
                        context.startActivity(intent);
                    }

                }

            });

            //찜하기 서버 체크
            tbFavor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position=getLayoutPosition();
                    CampingApi_General_RecyclerItem item=items.get(position);
                    item.favor=isChecked? 1:0; //변수 값 변경

                    if (item.favor==1){
                        Retrofit retrofit=RetrofitHelper.getRetrofitInstanceScalars();
                        RetrofitService retrofitService=retrofit.create(RetrofitService.class);
                        Call<String> call = retrofitService.insertfavor(item.name,item.lineintro,item.campingimg);
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Toast.makeText(context, ""+response.body(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else {

                        Retrofit retrofit=RetrofitHelper.getRetrofitInstanceScalars();
                        RetrofitService retrofitService=retrofit.create(RetrofitService.class);
                        Call<String> call = retrofitService.deletefavor(item.name);
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Toast.makeText(context, ""+response.body(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                }
            });


            //좋아요 서버 체크
            tbRecommend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position=getLayoutPosition();
                    CampingApi_General_RecyclerItem item=items.get(position);
                    item.favor=isChecked? 1:0; //변수 값 변경

                    Retrofit retrofit=RetrofitHelper.getRetrofitInstanceScalars();
                    RetrofitService retrofitService=retrofit.create(RetrofitService.class);

                    if (item.favor==1){
                        Call<String> call = retrofitService.insertrecommend(item.name,item.lineintro,item.campingimg);
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Toast.makeText(context, ""+response.body(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else {

                        Call<String> call = retrofitService.deleterecommend(item.name);
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Toast.makeText(context, ""+response.body(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                }
            });

        }
    }
}
