package com.hi1122.campinggo;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class Kakaologin extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //kakao SDK 초기화
        KakaoSdk.init(this,"a7e8ef6cbfca8a2e6f5b881598bc0863");
    }
}
