package com.hi1122.campinggo;

public class ReviewRecyclerItem {

    int no;
    String reviewiv;
    String loaddate;
    String title;
    String detail;
    int favor;
    String file;

    String datenow1;

    public static String datenow;

    public ReviewRecyclerItem() {
    }

    public ReviewRecyclerItem(int no, String reviewiv, String loaddate, String title, String detail, int favor, String file,String datenow1,String datenow) {
        this.no = no;
        this.reviewiv = reviewiv;
        this.loaddate = loaddate;
        this.title = title;
        this.detail = detail;
        this.favor = favor;
        this.file = file;
        this.datenow1=datenow1;
        this.datenow=datenow;
    }
}



