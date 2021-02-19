package com.hi1122.campinggo;

public class CampingApiRecyclerItem {

    public String name;   //facltNm
    public String lineintro; //한줄소개
    public String campingimg;  //firstImageUrl
    public String intro; //intro 소개
    public String induty; //induty 업종
    public String addr1; //addr1 주소
    public String addr2; //addr2 상세주소


    public CampingApiRecyclerItem(String name, String lineintro, String campingimg, String intro, String induty, String addr1, String addr2) {
        this.name = name;
        this.lineintro = lineintro;
        this.campingimg = campingimg;
        this.intro = intro;
        this.induty = induty;
        this.addr1 = addr1;
        this.addr2 = addr2;
    }


    public CampingApiRecyclerItem() {

    }

}
