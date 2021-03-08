package com.hi1122.campinggo;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Home_Second_Fragment extends Fragment {

    ArrayList<Home_Second_RecyclerItem> items=new ArrayList<>();
    RecyclerView recyclerView;
    Home_Second_RecyclerAdpter recyclerAdpter;
    AppCompatButton tipbtn;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
   
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_second_tab,container,false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.recycler);
        recyclerAdpter=new Home_Second_RecyclerAdpter(getActivity(),items);
        recyclerView.setAdapter(recyclerAdpter);

        tipbtn=view.findViewById(R.id.clicktip);
        tipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (G.nickname!=null) startActivity(new Intent(getActivity(),Home_Second_add.class));
                else Snackbar.make(getView(),"로그인이 필요한 서비스입니다.",Snackbar.LENGTH_SHORT).show();
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
        RetrofitServiceTip retrofitServicetip= retrofit.create(RetrofitServiceTip.class);
        Call<ArrayList<Home_Second_RecyclerItem>> call= retrofitServicetip.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<Home_Second_RecyclerItem>>() {
            @Override
            public void onResponse(Call<ArrayList<Home_Second_RecyclerItem>> call, Response<ArrayList<Home_Second_RecyclerItem>> response) {

                //기존데이터들 모두 제거
                items.clear();
                recyclerAdpter.notifyDataSetChanged();

                //결과로 받아온 ArrayList<MarketItem>을 items에 추가
                ArrayList<Home_Second_RecyclerItem> list= response.body();
                for(Home_Second_RecyclerItem item: list){
                    items.add(0, item);
                    recyclerAdpter.notifyItemInserted(0);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Home_Second_RecyclerItem>> call, Throwable t) {

            }
        });


    }
}
