package com.hi1122.campinggo;

public class MypageRecyclerItem {

    String ImgUrl;
    String title;
    String msg;
    String rating;

    public MypageRecyclerItem(String imgUrl, String title, String msg, String rating) {
        ImgUrl = imgUrl;
        this.title = title;
        this.msg = msg;
        this.rating = rating;
    }

    public MypageRecyclerItem() {
    }
}
