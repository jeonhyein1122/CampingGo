package com.hi1122.campinggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
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

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {
    
//    CircleImageView profile;
//    TextView tvnickname;
    String nickname;
    EditText login_eamil;
    EditText login_password;
    TextView login_button;
    TextView login_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        profile=findViewById(R.id.profile);
//        tvnickname=findViewById(R.id.tv_nickname);
        login_eamil=findViewById(R.id.login_etemail);
        login_password=findViewById(R.id.login_etpassword);
        login_button=findViewById(R.id.login_login);
        login_signup=findViewById(R.id.login_signup);

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

    }

    //툴바 백버튼 눌렀을때 홈으로 
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
    
    
    public void clicklogin(View view) {
        LoginClient.getInstance().loginWithKakaoAccount(this, new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                
                if(oAuthToken !=null){
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();

                    UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                        @Override
                        public Unit invoke(User user, Throwable throwable) {
                            if(user !=null){
                                long id=user.getId();
                                nickname=user.getKakaoAccount().getProfile().getNickname();
                                String profileImageUrl=user.getKakaoAccount().getProfile().getThumbnailImageUrl();

//                                 tvnickname.setText(nickname);
//                              Glide.with(LoginActivity.this).load(profileImageUrl).into(profile);
                                Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                                intent.putExtra("tvnicknameId",nickname);
                                startActivity(intent);


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
}