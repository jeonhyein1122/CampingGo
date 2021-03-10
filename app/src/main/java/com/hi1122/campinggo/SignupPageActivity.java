package com.hi1122.campinggo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupPageActivity extends AppCompatActivity {

    EditText et_id, et_pass, et_nickname;
    TextView btn_signup;
    ImageView profileimg;
    String imgPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        et_id=findViewById(R.id.signup_etid);
        et_pass=findViewById(R.id.signup_etpassword);
        et_nickname=findViewById(R.id.signup_etnickname);
        profileimg=findViewById(R.id.signup_profile);

//        Toolbar signuptoolbar=findViewById(R.id.signuptoolbar);
//        setSupportActionBar(signuptoolbar);
//        getActionBar().setDisplayHomeAsUpEnabled(true);

        //회원가입 버튼 클릭 시 수행
        btn_signup = findViewById( R.id.signup_signup );

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userID = et_id.getText().toString();
                String userPassword = et_pass.getText().toString();
                String userName = et_nickname.getText().toString();

                Retrofit retrofit= RetrofitHelper.getRetrofitInstanceScalars();
                RetrofitServiceSignup retrofitServicesigup= retrofit.create(RetrofitServiceSignup.class);

                MultipartBody.Part filePart= null;
                if(imgPath!=null){
                    File file= new File(imgPath);
                    RequestBody requestBody= RequestBody.create(MediaType.parse("image/*"), file);
                    filePart= MultipartBody.Part.createFormData("img", file.getName(), requestBody);
                }

                Map<String, String> dataPart= new HashMap<>();
                dataPart.put("userID", userID);
                dataPart.put("userPassword",userPassword);
                dataPart.put("userName", userName);

                Call<String> call= retrofitServicesigup.postDataToServer(dataPart, filePart);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String s= response.body();
                        Toast.makeText(SignupPageActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

                //업로드가 완료되면 액티비티 종료
                finish();


            }
        });

    }

    public void click_signup_Profile(View view) {

        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==10 && resultCode==RESULT_OK){
            Uri uri=data.getData();
            if(uri !=null){
                Glide.with(this).load(uri).into(profileimg);

                imgPath= getRealPathFromUri(uri);
            }
        }
    }

    //절대경로 c+v
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }
}