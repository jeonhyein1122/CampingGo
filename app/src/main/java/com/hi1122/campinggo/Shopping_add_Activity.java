package com.hi1122.campinggo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Shopping_add_Activity extends AppCompatActivity {

    EditText ettitle,etprice,etdetail;
    ImageView iv;
    TextView dialogtv;
    String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_add);

        ettitle=findViewById(R.id.shopping_add_ettitle);
        etprice=findViewById(R.id.shopping_add_etprice);
        etdetail=findViewById(R.id.shopping_add_etdetail);
        iv=findViewById(R.id.shopping_add_iv);
    }

    public void clickSelctImage(View view) {
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
                Glide.with(this).load(uri).into(iv);

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

    public void clickok(View view) {

        String title=ettitle.getText().toString();
        String price=etprice.getText().toString();
        String detail=etdetail.getText().toString();
        String usernickname= G.nickname;
        String userprofile= G.profile;


        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceScalars();
        RetrofitService retrofitService= retrofit.create(RetrofitService.class);

        MultipartBody.Part filePart= null;
        if(imgPath!=null){
            File file= new File(imgPath);
            RequestBody requestBody= RequestBody.create(MediaType.parse("image/*"), file);
            filePart= MultipartBody.Part.createFormData("img", file.getName(), requestBody);
        }

        Map<String, String> dataPart= new HashMap<>();
        dataPart.put("title", title);
        dataPart.put("price", price);
        dataPart.put("detail", detail);
        dataPart.put("nickname",usernickname);
        dataPart.put("userprofile",userprofile);


        Call<String> call= retrofitService.postDataToServer(dataPart, filePart);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s= response.body();
                Toast.makeText(Shopping_add_Activity.this, ""+s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Shopping_add_Activity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //소프트키
        InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        //업로드가 완료되면 액티비티 종료
        finish();


    }




    AlertDialog dialog;
    public void clickcancle(View view) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        LayoutInflater inflater=getLayoutInflater();
        LinearLayout layout=(LinearLayout)inflater.inflate(R.layout.dialog,null);

        dialogtv=layout.findViewById(R.id.dialogtv);

        builder.setView(layout);

        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


    }


    public void clickSelctImagebtn(View view) {

        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,10);

    }
}