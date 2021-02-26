package com.hi1122.campinggo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShoppingFragment extends Fragment {

    ArrayList<ShoppingRecyclerItem> items=new ArrayList<>();
    RecyclerView recyclerView;
    ShoppingRecyclerAdpter recyclerAdpter;
    Button btnadd;

    SwipeRefreshLayout refreshLayout;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_shopping,container,false);

       btnadd=view.findViewById(R.id.addShopping);

       btnadd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

              if (G.nickname !=null) startActivity(new Intent(getActivity(),Shopping_add_Activity.class));
              else startActivity(new Intent(getActivity(),LoginActivity.class));

           }
       });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.recycler);
        recyclerAdpter=new ShoppingRecyclerAdpter(getActivity(),items);
        recyclerView.setAdapter(recyclerAdpter);

        refreshLayout= view.findViewById(R.id.layout_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                refreshLayout.setRefreshing(false);
            }
        });

        String[] permissions= new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if( ActivityCompat.checkSelfPermission(getContext(), permissions[0]) == PackageManager.PERMISSION_DENIED ){
            ActivityCompat.requestPermissions(getActivity(), permissions, 100);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    void loadData(){

        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceGson();
        RetrofitService retrofitService= retrofit.create(RetrofitService.class);
        Call<ArrayList<ShoppingRecyclerItem>> call= retrofitService.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<ShoppingRecyclerItem>>() {
            @Override
            public void onResponse(Call<ArrayList<ShoppingRecyclerItem>> call, Response<ArrayList<ShoppingRecyclerItem>> response) {

                //기존데이터들 모두 제거
                items.clear();
                recyclerAdpter.notifyDataSetChanged();

                //결과로 받아온 ArrayList<MarketItem>을 items에 추가
                ArrayList<ShoppingRecyclerItem> list= response.body();
                for(ShoppingRecyclerItem item: list){
                    items.add(0, item);
                    recyclerAdpter.notifyItemInserted(0);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<ShoppingRecyclerItem>> call, Throwable t) {

            }
        });



    }


}
