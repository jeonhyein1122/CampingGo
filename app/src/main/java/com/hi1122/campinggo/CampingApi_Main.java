package com.hi1122.campinggo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

public class CampingApi_Main extends AppCompatActivity {

    ArrayList<CampingApiRecyclerItem> items=new ArrayList<>();
    RecyclerView recyclerView;
    CampingApiAdapter adapter;
    String tagName;
    String img;

    CampingApiRecyclerItem item= null;

    String apiKey="fPVCaLqlpYv1liNHjyn0aYOBfnyVvPtLiyXQO%2BHXpfaNP2SHBHzZjjZ8SQgYsxtxiVti1V6j4YmjHkf8O4mzQw%3D%3D";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camping_a_p_i_main);
//
//        items.add(new CampingApiRecyclerItem("왕십리 캠핑장","우와 신기해","https://newsimg.hankookilbo.com/cms/articlerelease/2020/12/30/60bbf7df-dc71-47f9-a31c-f2d61e0b97b8.jpg"));
//        items.add(new CampingApiRecyclerItem("성수 캠핑장","우와 신기해","https://newsimg.hankookilbo.com/cms/articlerelease/2020/12/30/60bbf7df-dc71-47f9-a31c-f2d61e0b97b8.jpg"));
//        items.add(new CampingApiRecyclerItem("춘의역 캠핑장","우와 신기해","https://newsimg.hankookilbo.com/cms/articlerelease/2020/12/30/60bbf7df-dc71-47f9-a31c-f2d61e0b97b8.jpg"));
//        items.add(new CampingApiRecyclerItem("광주광역시 캠핑장","우와 신기해","https://newsimg.hankookilbo.com/cms/articlerelease/2020/12/30/60bbf7df-dc71-47f9-a31c-f2d61e0b97b8.jpg"));
//        items.add(new CampingApiRecyclerItem("상왕십리 캠핑장","우와 신기해","https://newsimg.hankookilbo.com/cms/articlerelease/2020/12/30/60bbf7df-dc71-47f9-a31c-f2d61e0b97b8.jpg"));

        recyclerView = findViewById(R.id.recyclercampingapi);
        adapter = new CampingApiAdapter(this, items);
        recyclerView.setAdapter(adapter);

        Thread t = new Thread() {
            @Override

            public void run() {

                String address = "http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/basedList?ServiceKey="
                        + apiKey + "&MobileOS=AND" + "&MobileApp=campinggo&numOfRows=200";

                try {
                    URL url = new URL(address);
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp = factory.newPullParser();

                    xpp.setInput(isr);

                    int eventType = xpp.getEventType();

                    //StringBuffer buffer=null;
                    Map<String, String> dataPart= new HashMap<>();


                    while (eventType != XmlPullParser.END_DOCUMENT) {

                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:
                                break;

                            case XmlPullParser.START_TAG:
                                tagName = xpp.getName();
                                if (tagName.equals("item")) {
                                    item = new CampingApiRecyclerItem();

                                } else if (tagName.equals("firstImageUrl")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.campingimg = text;

                                } else if (tagName.equals("facltNm")) {
//                                    xpp.next();
//                                    item.addr+= xpp.getText();
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.name = text;

                                } else if (tagName.equals("lineIntro")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.lineintro = text;
                                } else if (tagName.equals("intro")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.intro = text;

                                } else if (tagName.equals("induty")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.induty = text;

                                } else if (tagName.equals("addr1")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.addr1 = text;
                                } else if (tagName.equals("addr2")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.addr2 = text;
                                } else if (tagName.equals("resveCl")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.resveCl = text;
                                } else if (tagName.equals("createdtime")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.createdtime = text;
                                } else if (tagName.equals("modifiedtime")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.modifiedtime = text;
                                } else if (tagName.equals("tel")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.tel = text;
                                } else if (tagName.equals("homepage")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.homepage = text;
                                } else if (tagName.equals("resveUrl")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.resveUrl = text;
                                }else if (tagName.equals("mapX")) {
                                    xpp.next();
//                                    xpp.getText();
                                    if (item != null) item.mapX =xpp.getText();
                                }else if (tagName.equals("mapY")) {
                                    xpp.next();
//                                    String text = xpp.getText();
                                    if (item != null) item.mapY =xpp.getText();
                                }else if (tagName.equals("contentId")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.contentId = text;
                                }


                                break;

                            case XmlPullParser.TEXT:
                                break;

                            case XmlPullParser.END_TAG:
                                String tagName2 = xpp.getName();
                                if (tagName2.equals("item")) {
                                    if (item.campingimg != null && item.campingimg !=null) {
                                        items.add(item);
                                    }

//                                    if (item.campingimg !=null && item.addr1=="강원"){
//                                        items.add(item);
//                                    }

//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            items.add(item);
////                                            Log.i("tagtag",items.size()+"");
//                                            adapter.notifyItemInserted(items.size()-1);
////                                            item=null;
//                                        }
//                                    });

                                }
                                break;
                        }//switch

                        eventType = xpp.next();

                    }//while

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }


            }
        };
        t.start();


    }

}