package com.hi1122.campinggo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class CampingApi_Detailmap extends AppCompatActivity {

    GoogleMap gmap;
    ArrayList<CampingApi_RecyclerItem> items=new ArrayList<>();
    SupportMapFragment mapFragment;
    String tagName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camping_apimap);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);


        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gmap=googleMap;
                gmap.getUiSettings().setZoomControlsEnabled(true);

                Intent intent1=getIntent();
                String detailmapX=intent1.getStringExtra("mapx");
                String detailmapY=intent1.getStringExtra("mapy");
                String detailnameId=intent1.getStringExtra("nameId");

                double lng=Double.parseDouble(detailmapX);  //경도
                double lat=Double.parseDouble(detailmapY); //위도


                LatLng camping=new LatLng(lat,lng);
                Log.i("la",detailmapX+","+detailmapY);
                gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(camping,15));

                    MarkerOptions marker=new MarkerOptions();
                    marker.title(detailnameId);
                    marker.position(camping);
//                    marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));

                    marker.anchor(0.5f, 1.0f);

                    gmap.addMarker(marker);
//

            }
        });


    }

 }
