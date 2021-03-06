package com.hi1122.campinggo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ClipData;
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

public class ShoppingRecyclerAdpter extends RecyclerView.Adapter {

    Context context;
    ArrayList<ShoppingRecyclerItem> items;

    public ShoppingRecyclerAdpter(Context context, ArrayList<ShoppingRecyclerItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.shopping_recycler_item,parent,false);
        VH holder=new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh=(VH)holder;

        ShoppingRecyclerItem item=items.get(position);

        ((VH) holder).tvTitle.setText(item.title+"");
        ((VH) holder).tvprice.setText(item.price+"원");

        String imgUrl="http://jhyein1122.dothome.co.kr/Campinggo/"+item.file;
        Log.i("tagshad",imgUrl);

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
      TextView tvprice;
      ToggleButton tbFavor;


        public VH(@NonNull View itemView) {
            super(itemView);

            iv=itemView.findViewById(R.id.shopping_iv);
            tvTitle=itemView.findViewById(R.id.shopping_title);
            tvprice=itemView.findViewById(R.id.shopping_price);
            tbFavor=itemView.findViewById(R.id.tb_favor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getLayoutPosition();
                    Toast.makeText(context, "클릭됨", Toast.LENGTH_SHORT).show();

                    String shoppingiv=items.get(position).file;
                    String title=items.get(position).title;
                    String price=items.get(position).price;
                    String detail=items.get(position).detail;

                    Intent intent=new Intent(context,Shopping_DetailActivity.class);
                    intent.putExtra("shoppingiv",shoppingiv);
                    Log.i("tag1",shoppingiv+"");
                    intent.putExtra("title",title);
                    intent.putExtra("price",price);
                    intent.putExtra("detail",detail);

                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity)context,new Pair<View,String>(iv,"shoppingimg"));
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
                    ShoppingRecyclerItem item=items.get(position);
                    item.favor=isChecked? 1:0; //변수 값 변경

                    //db update
                    Retrofit retrofit=RetrofitHelper.getRetrofitInstanceGson();
                    RetrofitService retrofitService=retrofit.create(RetrofitService.class);
                    Call<ShoppingRecyclerItem> call= retrofitService.updateData("updateFavor.php", item);
                    call.enqueue(new Callback<ShoppingRecyclerItem>() {
                        @Override
                        public void onResponse(Call<ShoppingRecyclerItem> call, Response<ShoppingRecyclerItem> response) {

                        }
                        @Override
                        public void onFailure(Call<ShoppingRecyclerItem> call, Throwable t) {


                        }
                    });

                }
            });
        }
    }
}
