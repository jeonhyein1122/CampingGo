package com.hi1122.campinggo;

import com.google.gson.annotations.SerializedName;

public class ShoppingRecyclerItem {

    int no;
    String shoppingiv;
    String title;
    String price;
    String detail;
    int favor;
    String date;
    String file;
    String userprofile;
    String nickname;



    public ShoppingRecyclerItem() {
    }



    public ShoppingRecyclerItem(int no, String shoppingiv, String title, String price, String detail, int favor, String date,String file,String nickname,String userprofile) {
        this.no = no;
        this.shoppingiv = shoppingiv;
        this.title = title;
        this.price = price;
        this.detail = detail;
        this.favor = favor;
        this.date = date;
        this.file=file;
        this.userprofile=userprofile;
        this.nickname=nickname;

    }
}
