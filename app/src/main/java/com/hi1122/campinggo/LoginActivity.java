package com.hi1122.campinggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    
//    CircleImageView profile;
//    TextView tvnickname;
    String nickname;
    EditText login_id;
    EditText login_password;
    TextView login_button;
    TextView login_signup;

    ArrayList<LoginItem> items=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        profile=findViewById(R.id.profile);
//        tvnickname=findViewById(R.id.tv_nickname);

        login_id=findViewById(R.id.login_etemail);
        login_password=findViewById(R.id.login_etpassword);
        login_button=findViewById(R.id.login_login);
        login_signup=findViewById(R.id.login_signup);


        loadData();

       Toolbar logintoolbar=findViewById(R.id.logintoolbar);
       setSupportActionBar(logintoolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       login_signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(LoginActivity.this, SignupPageActivity.class);
               startActivity(intent);
           }
       });

       login_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
//               Toast.makeText(LoginActivity.this, "클릭됨", Toast.LENGTH_SHORT).show();
               String userID = login_id.getText().toString();
               String userPassword = login_password.getText().toString();


               Retrofit retrofit=RetrofitHelper.getRetrofitInstanceGson();
               RetrofitServiceSignup retrofitServicesignup=retrofit.create(RetrofitServiceSignup.class);
               Call<LoginItem> call=retrofitServicesignup.User(userID,userPassword);
               call.enqueue(new Callback<LoginItem>() {
                   @Override
                   public void onResponse(Call<LoginItem> call, Response<LoginItem> response) {

                    LoginItem item=response.body();

                     if (item!=null){

                        G.userID=item.userID;
                        G.nickname=item.userName;
                        G.profile="http://jhyein1122.dothome.co.kr/Campinggosignup/"+item.file;

                         // SharedPreferences 에 저장 (내부저장소에 데이터를 영구히 저장하는 녀석)
                         SharedPreferences pref= getSharedPreferences("account", MODE_PRIVATE);
                         SharedPreferences.Editor editor= pref.edit();

                         editor.putString("nickName", G.nickname);
                         editor.putString("profile", G.profile);

                         editor.commit();


                         startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();

                       } else Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show();

                   }

                   @Override
                   public void onFailure(Call<LoginItem> call, Throwable t) {
                       Toast.makeText(LoginActivity.this, "서버 에러! 잠시후 다시 시도해 주세요", Toast.LENGTH_SHORT).show();
                   }


               });

           }
       });

    }

    //툴바 백버튼 눌렀을때 홈으로 
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
    
    
    public void clickkakaologin(View view) {
        LoginClient.getInstance().loginWithKakaoAccount(this, new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                
                if(oAuthToken !=null){
                    Toast.makeText( getApplicationContext(), "환영합니다", Toast.LENGTH_SHORT ).show();


                    UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                        @Override
                        public Unit invoke(User user, Throwable throwable) {
                            if(user !=null){
                                long id=user.getId();
                                nickname=user.getKakaoAccount().getProfile().getNickname();
                                String profileImageUrl=user.getKakaoAccount().getProfile().getThumbnailImageUrl();

                                G.nickname=nickname;
                                G.profile=profileImageUrl;


                                SharedPreferences pref= getSharedPreferences("account", MODE_PRIVATE);
                                SharedPreferences.Editor editor= pref.edit();

                                editor.putString("nickName", G.nickname);
                                editor.putString("profileUrl", G.profile);

                                editor.commit();
                                setResult(RESULT_OK);
                                finish();
//                                 tvnickname.setText(nickname);
//                              Glide.with(LoginActivity.this).load(profileImageUrl).into(profile);
//                                Intent intent= new Intent(LoginActivity.this,MainActivity.class);
//                                intent.putExtra("tvnicknameId",nickname);
//                                startActivity(intent);


                            }else {
                                Toast.makeText(LoginActivity.this, "사용자 정보요청 실패"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            return null;
                        }
                    });
                }else {
                    Toast.makeText(LoginActivity.this, "로그인 실패"+throwable.getMessage(), Toast.LENGTH_SHORT).show();

                }
                return null;
            }
        });
    }

    public void clicksignup(View view) {

        Intent intent= new Intent(this, SignupPageActivity.class);
        startActivity(intent);
    }

    void loadData(){
        SharedPreferences pref= getSharedPreferences("account", MODE_PRIVATE);
        G.nickname= pref.getString("nickName", null);
        G.profile= pref.getString("profileUrl", null);
    }
}