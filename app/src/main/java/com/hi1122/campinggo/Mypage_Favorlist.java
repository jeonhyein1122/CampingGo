package com.hi1122.campinggo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Mypage_Favorlist extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<ShoppingRecyclerItem> items = new ArrayList<>();
    ShoppingRecyclerAdpter recyclerAdpter;


    ArrayList<CampingApi_RecyclerItem> items2=new ArrayList<>();
    RecyclerView recyclerView2;
    CampingApi_Adapter recyclerAdpter2;

    Spinner spinner;
    ArrayAdapter adapter;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage__favorlist);


        recyclerView = findViewById(R.id.recycler);
        recyclerAdpter = new ShoppingRecyclerAdpter(this, items);
        recyclerView.setAdapter(recyclerAdpter);

        spinner=findViewById(R.id.spinner);
        adapter=ArrayAdapter.createFromResource(this,R.array.datas,R.layout.spinner_selected);
        spinner.setAdapter(adapter);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("찜한목록");

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Mypage_Favorlist.this, ""+position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    void loadData() {

            Retrofit retrofit = RetrofitHelper.getRetrofitInstanceGson();
//            RetrofitService retrofitService = retrofit.create(RetrofitService.class);
//            Call<ArrayList<ShoppingRecyclerItem>> call = retrofitService.loadDataFromServershopping();
//            call.enqueue(new Callback<ArrayList<ShoppingRecyclerItem>>() {
//                @Override
//                public void onResponse(Call<ArrayList<ShoppingRecyclerItem>> call, Response<ArrayList<ShoppingRecyclerItem>> response) {
//
//                    //기존데이터들 모두 제거
//                    items.clear();
//                    recyclerAdpter.notifyDataSetChanged();
//
//                    //결과로 받아온 ArrayList<MarketItem>을 items에 추가
//                    ArrayList<ShoppingRecyclerItem> list = response.body();
//                    for (ShoppingRecyclerItem item : list) {
//                        items.add(0, item);
//                        recyclerAdpter.notifyItemInserted(0);
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<ArrayList<ShoppingRecyclerItem>> call, Throwable t) {
////                Toast.makeText(getActivity(), "error: ReviewFrag--"+t.getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//            });


        RetrofitService retrofitService=retrofit.create(RetrofitService.class);
        Call<ArrayList<CampingApi_RecyclerItem>> call2=retrofitService.loadDataFromServerapi();
        call2.enqueue(new Callback<ArrayList<CampingApi_RecyclerItem>>() {
            @Override
            public void onResponse(Call<ArrayList<CampingApi_RecyclerItem>> call, Response<ArrayList<CampingApi_RecyclerItem>> response) {
                items2.clear();
                recyclerAdpter2.notifyDataSetChanged();

                ArrayList<CampingApi_RecyclerItem> list=response.body();
                for (CampingApi_RecyclerItem item:list){
                    items2.add(0,item);
                    recyclerAdpter2.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CampingApi_RecyclerItem>> call, Throwable t) {

            }
        });
    }
}
