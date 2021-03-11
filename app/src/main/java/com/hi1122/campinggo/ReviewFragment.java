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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ReviewFragment extends Fragment {

    //todo: 리뷰 좋아요버튼 추천 버튼으로 바꾸고 숫자 올라가는거 해서 main 리사이클러뷰에 붙이기 / 데이터 베이스에 이름,프로필 추가

    ArrayList<ReviewRecyclerItem> items=new ArrayList<>();
    RecyclerView recyclerView;
    ReviewRecyclerAdpter recyclerAdpter;
    FloatingActionButton btnadd;

    SwipeRefreshLayout refreshLayout;

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

               if (G.nickname !=null) startActivity(new Intent(getActivity(),Review_add_Activity.class));
               else startActivity(new Intent(getActivity(),LoginActivity.class));

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

        RetrofitServiceReview retrofitServiceReview= retrofit.create(RetrofitServiceReview.class);
        Call<ArrayList<ReviewRecyclerItem>> call= retrofitServiceReview.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<ReviewRecyclerItem>>() {
            @Override
            public void onResponse(Call<ArrayList<ReviewRecyclerItem>> call, Response<ArrayList<ReviewRecyclerItem>> response) {

                //기존데이터들 모두 제거
                items.clear();
                recyclerAdpter.notifyDataSetChanged();


                //결과로 받아온 ArrayList<MarketItem>을 items에 추가
                ArrayList<ReviewRecyclerItem> list= response.body();
                for(ReviewRecyclerItem item: list){
                    items.add(0, item);
                    recyclerAdpter.notifyItemInserted(0);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<ReviewRecyclerItem>> call, Throwable t) {
//                Toast.makeText(getActivity(), "error: ReviewFrag--"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }


}
