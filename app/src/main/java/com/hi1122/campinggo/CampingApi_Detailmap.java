package com.hi1122.campinggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CampingApi_Detailmap extends AppCompatActivity {

    GoogleMap gmap;
    ArrayList<CampingApiRecyclerItem> items=new ArrayList<>();
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
                    marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                    marker.anchor(0.5f, 1.0f);

                    gmap.addMarker(marker);
//

            }
        });


    }

 }
