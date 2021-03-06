package com.hi1122.campinggo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class MypageFragment extends Fragment {

    ArrayList<MypageRecyclerItem> items=new ArrayList<>();
    RecyclerView recyclerView;
    MypageRecyclerAdpter recyclerAdpter;
    CircleImageView profile;
    TextView nickname;
    TextView logout;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_mypage,container,false);

       //안돌아감..//
        profile=view.findViewById(R.id.mypage_profile);
        nickname=view.findViewById(R.id.mypage_nickname);

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
                            logout.setEnabled(false);
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

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.recycler);
        recyclerAdpter=new MypageRecyclerAdpter(getActivity(),items);
        recyclerView.setAdapter(recyclerAdpter);

        profile=view.findViewById(R.id.mypage_profile);
        nickname=view.findViewById(R.id.mypage_nickname);
        if (G.nickname !=null) nickname.setText(G.nickname);
        if (G.profile != null) Glide.with(this).load(G.profile).into(profile);



    }

    void loadData(){

        items.add(new MypageRecyclerItem());
        items.add(new MypageRecyclerItem());
        items.add(new MypageRecyclerItem());
        items.add(new MypageRecyclerItem());
        items.add(new MypageRecyclerItem());
        items.add(new MypageRecyclerItem());
        items.add(new MypageRecyclerItem());
        items.add(new MypageRecyclerItem());



    }


}
