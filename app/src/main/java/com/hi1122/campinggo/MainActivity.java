package com.hi1122.campinggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    Fragment[] fragments=new Fragment[5];
    FragmentManager fragmentManager;

//    TextView tv;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //툴바 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bnv = findViewById(R.id.bnv);


        drawerLayout = findViewById(R.id.layout_drawer);
        navigationView = findViewById(R.id.naviV);
        navigationView.setItemIconTintList(null);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction tran = fragmentManager.beginTransaction();
        fragments[0] = new HomeFragment();
        tran.add(R.id.container, fragments[0]);
        tran.commit();

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction tran = fragmentManager.beginTransaction();
                if (fragments[0] != null) tran.hide(fragments[0]);
                if (fragments[1] != null) tran.hide(fragments[1]);
                if (fragments[2] != null) tran.hide(fragments[2]);
                if (fragments[3] != null) tran.hide(fragments[3]);

                switch (item.getItemId()) {
                    case R.id.home:
                        tran.show(fragments[0]);

                        break;

                    case R.id.myaround :
                        if(fragments[1]==null){
                            fragments[1]=new MyAroundFragment();
                            tran.add(R.id.container,fragments[1]);
                            getSupportActionBar().setTitle("내주변");

                        }
                        tran.show(fragments[1]);
                        break;

                    case R.id. shopping:
                        if(fragments[2]==null){
                            fragments[2]=new ShoppingFragment();
                            tran.add(R.id.container,fragments[2]);
                            getSupportActionBar().setTitle("중고장터");
                        }
                        tran.show(fragments[2]);

                        break;

                    case R.id.review :
                        if(fragments[3]==null){
                            fragments[3]=new ReviewFragment();
                            tran.add(R.id.container,fragments[3]);
                            getSupportActionBar().setTitle("리뷰");
                        }
                        tran.show(fragments[3]);

                        break;
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