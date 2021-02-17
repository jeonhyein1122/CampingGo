package com.hi1122.campinggo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.kakao.sdk.common.util.Utility;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    Fragment[] fragments=new Fragment[5];
    FragmentManager fragmentManager;

//    TextView tv;
    Button loginBtn;
    TextView tvnickname;
    CircleImageView profile;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String keyHash= Utility.INSTANCE.getKeyHash(this);
        Log.i("KeyHash",keyHash);

        //툴바 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bnv = findViewById(R.id.bnv);


        drawerLayout = findViewById(R.id.layout_drawer);
        navigationView = findViewById(R.id.naviV);
        navigationView.setItemIconTintList(null);

        loginBtn=findViewById(R.id.loginbtn);
        tvnickname=findViewById(R.id.tv_nickname);
        profile=findViewById(R.id.profile);

//        //drawer header button login화면 클릭

//        final View headerView= navigationView.getHeaderView(0);
//        headerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Btnlogin=findViewById(R.id.loginbtn);
//                Btnlogin.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
////                        bnv.setSelectedItemId(R.id.mypage);
//                        drawerLayout.closeDrawer(navigationView);
//                    }
//                });
//
//            }
//        });

//
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
                if (fragments[4] != null) tran.hide(fragments[4]);

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

                    case R.id.mypage :

                        if(fragments[4]==null){
                            fragments[4]=new MypageFragment();
                            tran.add(R.id.container,fragments[4]);
                            getSupportActionBar().setTitle("마이페이지");
                        }
                        tran.show(fragments[4]);

                        break;
                }
                tran.commit();

                return true;
            }
        });

    }

    public void clickLoginBtn(View view){

        Intent intent=new Intent(this,LoginActivity.class);
//        startActivityForResult(intent,11);
        startActivity(intent);

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode){
//            case 11:
//                if(resultCode==RESULT_OK){
//                    String nickname=data.getStringExtra("tvnickname");
//                    String profile=data.getStringExtra("profileImageUrl");
//
//                    tvnickname.setText(nickname);
//
//
//                }else {
//                    tvnickname.setText("실패");
//                }
//                break;
//        }
//    }

    public void clickSignupBtn(View view){

        Intent intent=new Intent(this,SingupPageActivity.class);
        startActivity(intent);

    }

    public void clickmypage(View view){
        bnv.setSelectedItemId(R.id.mypage);
        drawerLayout.closeDrawer(navigationView);
    }


}