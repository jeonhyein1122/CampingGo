package com.hi1122.campinggo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.kakao.sdk.common.util.Utility;
import com.kakao.sdk.user.UserApiClient;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

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

    Button logoutBtn;
    TextView tvlogin;
    TextView tvlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String keyHash= Utility.INSTANCE.getKeyHash(this);
//        Log.i("KeyHash",keyHash);

        //툴바 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bnv = findViewById(R.id.bnv);


        drawerLayout = findViewById(R.id.layout_drawer);
        navigationView = findViewById(R.id.naviV);

//        profile=navigationView.findViewById(R.id.profile);

        navigationView.setItemIconTintList(null);

        loginBtn=findViewById(R.id.loginbtn);

//        drawer_header=findViewById(R.id.drawer_header);


//        LayoutInflater inflater = getLayoutInflater().from(this);
//        View header = inflater.inflate(R.layout.drawer_header,null  );
//        profile = header.findViewById(R.id.profile);
//        Log.i("profile",profile+"$$$$$$"+bnv);




//        //drawer header button login화면 클릭

        final View headerView= navigationView.getHeaderView(0);
        CircleImageView userprofile=headerView.findViewById(R.id.drawer_profile);
        TextView usernickname=headerView.findViewById(R.id.drawer_nickname);
        if (G.nickname !=null) usernickname.setText(G.nickname);
        if (G.profile != null) Glide.with(this).load(G.profile).into(userprofile);


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
//            }
//        });


        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);

        drawerToggle.syncState();



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                item.setChecked(true);
                drawerLayout.closeDrawers();

                int id=item.getItemId();
//                if (id==R.id.menu_all){
//                    startActivity(new Intent(getApplicationContext(),CampingApi_Main.class));
//                }

                switch (item.getItemId()){
                    case R.id.menu_all :
                        startActivity(new Intent(getApplicationContext(),CampingApi_Main.class));
                        drawerLayout.closeDrawer(navigationView);
                        break;

//                    case R.id.menu_mou :
//                        bnv.setSelectedItemId(R.id.myaround);
//                        drawerLayout.closeDrawer(navigationView);
//                        break;

                    case R.id.menu_mapicon :
                        bnv.setSelectedItemId(R.id.myaround);
                        drawerLayout.closeDrawer(navigationView);
                        break;

                    case R.id.menu_shop :
                        bnv.setSelectedItemId(R.id.shopping);
                        drawerLayout.closeDrawer(navigationView);
                        break;

                    case R.id.menu_review :
                        bnv.setSelectedItemId(R.id.review);
                        drawerLayout.closeDrawer(navigationView);
                        break;

                }

                return true;
            }
        });


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
                        getSupportActionBar().setTitle("campinggo");


                        break;

                    case R.id.myaround :
                        if(fragments[1]==null){
                            fragments[1]=new MyAroundFragment();
                            tran.add(R.id.container,fragments[1]);

                        }
                        tran.show(fragments[1]);
                        getSupportActionBar().setTitle("내주변");
                        break;

                    case R.id. shopping:
                        if(fragments[2]==null){
                            fragments[2]=new ShoppingFragment();
                            tran.add(R.id.container,fragments[2]);
                        }
                        tran.show(fragments[2]);
                        getSupportActionBar().setTitle("중고장터");
                        break;

                    case R.id.review :
                        if(fragments[3]==null){
                            fragments[3]=new ReviewFragment();
                            tran.add(R.id.container,fragments[3]);
                        }
                        tran.show(fragments[3]);
                        getSupportActionBar().setTitle("리뷰");
                        break;

                    case R.id.mypage :

                        if(fragments[4]==null){
                            fragments[4]=new MypageFragment();
                            tran.add(R.id.container,fragments[4]);
                            getSupportActionBar().setTitle("마이페이지");
                            tran.hide(fragments[4]);
                        }
                        if (G.nickname !=null || G.userID !=null) {
                            tran.show(fragments[4]);
                        }else {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivityForResult(intent, 1122);
                        }
                        break;
                }
                tran.commit();

                return true;
            }
        });



        //지도 동적퍼미션
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if( checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED ){
                String[] permissions= new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 0);
            }
        }


//        if (G.nickname !=null){
//            tvnickname.setText(G.nickname);
//
//        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//////        Log.i("nickname",);
////        if (G.profile !=null){
////            Glide.with(this).load(G.profile).into(iv);
////            Log.i("nickname",G.profile);
//
//        }
//    }

    public void clickLoginBtn(View view){

        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);

    }


    public void clickSignupBtn(View view){

        Intent intent=new Intent(this, SignupPageActivity.class);
        startActivity(intent);

    }

    public void clickmypage(View view){
        bnv.setSelectedItemId(R.id.mypage);
        drawerLayout.closeDrawer(navigationView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1122:
                if (resultCode==RESULT_OK) bnv.setSelectedItemId(R.id.mypage);
                else Toast.makeText(this, "안됨, 실패", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}