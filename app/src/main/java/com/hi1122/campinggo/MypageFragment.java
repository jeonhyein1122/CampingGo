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

import com.bumptech.glide.Glide;
import com.kakao.sdk.user.UserApiClient;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MypageFragment extends Fragment {

    //todo: 찜목록 - 캠핑장, 중고장터 올리기

//    ArrayList<MypageRecyclerItem> items=new ArrayList<>();
    RecyclerView recyclerView;
//    MypageRecyclerAdpter recyclerAdpter;
    CircleImageView profile;
    TextView nickname;
    TextView logout;
    Button favorlist;
    Button setting;

    Context context;
//    ArrayList<ShoppingRecyclerItem> items=new ArrayList<>();
//    ShoppingRecyclerAdpter recyclerAdpter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_mypage,container,false);

       //안돌아감..//
        profile=view.findViewById(R.id.mypage_profile);
        nickname=view.findViewById(R.id.mypage_nickname);
        favorlist=view.findViewById(R.id.favorlist);
        setting=view.findViewById(R.id.setting);

        if (G.nickname !=null) nickname.setText(G.nickname+" 님");
        if (G.profile != null) Glide.with(this).load(G.profile).into(profile);

        logout=view.findViewById(R.id.Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "로그아웃클릭됨", Toast.LENGTH_SHORT).show();
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        if(throwable !=null) {

                        } else {
                            Toast.makeText(getActivity(), "로그아웃", Toast.LENGTH_SHORT).show();

                            //로그인 회원정보 화면들 모두 초기화
                            nickname.setText(" ");
                            Glide.with(getActivity()).load(R.drawable.profilenull).into(profile);
                            startActivity(new Intent(getActivity(),LoginActivity.class));
                        }
                        return null;
                    }
                });

            }
        });

        favorlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Mypage_Favorlist.class);
                startActivity(intent);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Mypage_setting.class));
            }
        });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        recyclerView=view.findViewById(R.id.recycler);
//        recyclerAdpter=new ShoppingRecyclerAdpter(getActivity(),items);
//        recyclerView.setAdapter(recyclerAdpter);

        profile=view.findViewById(R.id.mypage_profile);
        nickname=view.findViewById(R.id.mypage_nickname);
        if (G.nickname !=null) nickname.setText(G.nickname);
        if (G.profile != null) Glide.with(this).load(G.profile).into(profile);


    }

    @Override
    public void onResume() {
        super.onResume();
//        loadData();
    }

//    void loadData(){
//
//        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceGson();
//        RetrofitService retrofitService= retrofit.create(RetrofitService.class);
//        Call<ArrayList<ShoppingRecyclerItem>> call= retrofitService.loadDataFromServershopping();
//        call.enqueue(new Callback<ArrayList<ShoppingRecyclerItem>>() {
//            @Override
//            public void onResponse(Call<ArrayList<ShoppingRecyclerItem>> call, Response<ArrayList<ShoppingRecyclerItem>> response) {
//
//                //기존데이터들 모두 제거
//                items.clear();
//                recyclerAdpter.notifyDataSetChanged();
//
//                //결과로 받아온 ArrayList<MarketItem>을 items에 추가
//                ArrayList<ShoppingRecyclerItem> list= response.body();
//                for(ShoppingRecyclerItem item: list){
//                    items.add(0, item);
//                    recyclerAdpter.notifyItemInserted(0);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<ShoppingRecyclerItem>> call, Throwable t) {
////                Toast.makeText(getActivity(), "error: ReviewFrag--"+t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//






}
