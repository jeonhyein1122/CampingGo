package com.hi1122.campinggo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Home_First_Fragment extends Fragment {

    Context context;
    ArrayList<ReviewRecyclerItem> items=new ArrayList<>();

    RecyclerView recyclerView;
    ReviewRecyclerAdpter recyclerAdpter;
    Button Btnall;
    Button Btnmountain;
    TextView more;

    ArrayList<CampingApiRecyclerItem> items2=new ArrayList<>();
    RecyclerView recyclerView2;
    CampingApiAdapter recyclerAdpter2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.home_first_tab,container,false);

        //open api 전체 버튼
        Btnall=view.findViewById(R.id.btnall);
        Btnall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CampingApi_Main.class); //전체 버튼에 들어갈 클래스 만들기
                startActivity(intent);

            }
        });


        Btnmountain=view.findViewById(R.id.btnmountain);
        Btnmountain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Home_First_Tab_mountainbtn.class));
            }
        });

        more=view.findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //todo : 인텐트하깅
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

        recyclerView2=view.findViewById(R.id.recycler2);
        recyclerAdpter2=new CampingApiAdapter(getActivity(),items2);
        recyclerView2.setAdapter(recyclerAdpter2);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    void loadData(){

        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceGson();
        RetrofitServiceReview retrofitServiceReview= retrofit.create(RetrofitServiceReview.class);
        Call<ArrayList<ReviewRecyclerItem>> call= retrofitServiceReview.loadDataFromServerreview();
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

       RetrofitService retrofitService=retrofit.create(RetrofitService.class);
       Call<ArrayList<CampingApiRecyclerItem>> call2=retrofitService.loadDataFromServerapi1();
       call2.enqueue(new Callback<ArrayList<CampingApiRecyclerItem>>() {
           @Override
           public void onResponse(Call<ArrayList<CampingApiRecyclerItem>> call, Response<ArrayList<CampingApiRecyclerItem>> response) {
               items2.clear();
               recyclerAdpter2.notifyDataSetChanged();

               ArrayList<CampingApiRecyclerItem> list=response.body();
               for (CampingApiRecyclerItem item:list){
                   items2.add(0,item);
                   recyclerAdpter2.notifyItemInserted(0);
               }
           }

           @Override
           public void onFailure(Call<ArrayList<CampingApiRecyclerItem>> call, Throwable t) {

           }
       });

//        items2.add(new CampingApiRecyclerItem());
//        items2.add(new CampingApiRecyclerItem());
//        items2.add(new CampingApiRecyclerItem());
//        items2.add(new CampingApiRecyclerItem());
//        items2.add(new CampingApiRecyclerItem());


    }
}

