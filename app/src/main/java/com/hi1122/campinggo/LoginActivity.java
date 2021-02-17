package com.hi1122.campinggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {
    
    CircleImageView profile;
    TextView tvnickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        profile=findViewById(R.id.profile);
        tvnickname=findViewById(R.id.tv_nickname);

       Toolbar logintoolbar=findViewById(R.id.logintoolbar);
       setSupportActionBar(logintoolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                                String nickname=user.getKakaoAccount().getProfile().getNickname();
                                String profileImageUrl=user.getKakaoAccount().getProfile().getThumbnailImageUrl();

                                 tvnickname.setText(nickname);

                              Glide.with(LoginActivity.this).load(profileImageUrl).into(profile);

//                                Intent intent= getIntent();
//                                intent.putExtra("tvnickname",nickname);
//                                intent.putExtra("profileImageUrl",profileImageUrl);
//                              setResult(RESULT_OK,intent);
//                              finish();

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

        Intent intent= new Intent(this,SingupPageActivity.class);
        startActivity(intent);
    }
}