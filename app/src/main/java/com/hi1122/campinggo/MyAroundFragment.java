package com.hi1122.campinggo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyAroundFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap gMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_myaround,container,false);

        MapView mapview=(MapView)view.findViewById(R.id.map);
        mapview.onCreate(savedInstanceState);
        mapview.onResume();
        mapview.getMapAsync(this);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //여기서 xml이 뷰들에 대한 find 작업
    }

    //
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //파라미터로 전달된 GoogleMap이 지도 객체임!
        gMap = googleMap; //멤버변수에 대입하면 이 객체를 다른 메소드에서도 사용 가능

        //지도의 특정좌표로 이동 및 줌인
        LatLng seoul = new LatLng(37.562087, 127.035192);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 15)); //줌:1~25

        //마커추가하기
        MarkerOptions marker = new MarkerOptions();
        marker.title("왕십리");
        marker.snippet("왕십리역에 있는 미래능력개발교육원");
        marker.position(seoul);
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_flag_36)); //아이콘이미지는 벡터이미지 안됨, .jpg or .png같은 픽셀이미지여야만 함
        marker.anchor(0.5f, 1.0f);

        gMap.addMarker(marker);

        //지도의 대표적인 설정들
        UiSettings settings = gMap.getUiSettings();
        settings.setZoomControlsEnabled(true);

        //내 위치 탐색을 지도 라이브러리에서 제공해줌..
        settings.setMyLocationButtonEnabled(true);

        //내위치를 요구해야 버튼 보여짐 (명시적 퍼미션 체크코드 필요)
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;

        }
        gMap.setMyLocationEnabled(true);

        //나머지 관련 내용을 개발자사이트의 가이드를 참고해서 시도해보세요~~


    }
}

