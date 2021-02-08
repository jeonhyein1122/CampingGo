package com.hi1122.campinggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    Fragment[] fragments=new Fragment[5];
    FragmentManager fragmentManager;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //툴바 설정
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bnv=findViewById(R.id.bnv);
        tv=findViewById(R.id.tv);

        fragmentManager=getSupportFragmentManager();
        FragmentTransaction tran=fragmentManager.beginTransaction();
        fragments[0]=new HomeFragment();
        tran.add(R.id.container,fragments[0]);
        tran.commit();

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction tran=fragmentManager.beginTransaction();
                if(fragments[0] !=null) tran.hide(fragments[0]);
                if(fragments[1] !=null) tran.hide(fragments[1]);

                switch (item.getItemId()) {
                    case R.id.home:
                        tran.show(fragments[0]);
//                      tv.setText("뭐먹지");
                        break;

//                    case R.id.video :
//                        if(fragments[1]==null){
//                            fragments[1]=new FavoriteFragment();
//                            tran.add(R.id.container,fragments[1]);
//                        }
//                        tran.show(fragments[1]);
//                        break;
//
//                    case R.id. favorite:
//                        if(fragments[1]==null){
//                            fragments[1]=new FavoriteFragment();
//                            tran.add(R.id.container,fragments[1]);
//                        }
//                        tran.show(fragments[1]);
//
//                        break;

//                    case R.id.orderlist :
//                        break;
//
//                    case R.id.mybeamin :
//                        break;
                }
                tran.commit();
                return true;
            }
        });

    }
}