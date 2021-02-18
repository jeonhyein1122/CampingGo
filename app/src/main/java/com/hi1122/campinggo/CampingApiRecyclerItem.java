package com.hi1122.campinggo;

public class CampingApiRecyclerItem {

    public String title;
    public String subtitle;
    int campingimg;


    public CampingApiRecyclerItem() {
    }


    public CampingApiRecyclerItem(String title, String subtitle,int campingimg) {
        this.title = title;
        this.subtitle = subtitle;
        this.campingimg=campingimg;
    }
}
