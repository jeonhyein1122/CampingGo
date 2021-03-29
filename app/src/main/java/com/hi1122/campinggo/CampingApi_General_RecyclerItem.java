package com.hi1122.campinggo;

public class CampingApi_General_RecyclerItem {

    public String name;   //facltNm
    public String lineintro; //한줄소개
    public String campingimg;  //firstImageUrl
    public String intro; //intro 소개
    public String induty; //induty 업종
    public String addr1; //addr1 주소
    public String addr2; //addr2 상세주소
    public String resveCl;//실시간 예약 주소
    public String createdtime;//등록일
    public String modifiedtime;//수정일
    public String tel;//수정일
    public String homepage;//수정일
    public String resveUrl;//수정일
    public String mapX;//맵x
    public String mapY;//맵y
    public String contentId;//컨텐츠 id
    public String lctCl; //입지구분

    int favor;
    int recommend;
    String userID;

    public CampingApi_General_RecyclerItem() {
    }

    public CampingApi_General_RecyclerItem(String name, String lineintro, String campingimg, String intro, String induty, String addr1, String addr2, String resveCl, String createdtime, String modifiedtime, String tel, String homepage, String resveUrl, String mapX, String mapY, String contentId, String lctCl, int favor, int recommend, String userID) {
        this.name = name;
        this.lineintro = lineintro;
        this.campingimg = campingimg;
        this.intro = intro;
        this.induty = induty;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.resveCl = resveCl;
        this.createdtime = createdtime;
        this.modifiedtime = modifiedtime;
        this.tel = tel;
        this.homepage = homepage;
        this.resveUrl = resveUrl;
        this.mapX = mapX;
        this.mapY = mapY;
        this.contentId = contentId;
        this.lctCl=lctCl;
        this.favor = favor;
        this.recommend = recommend;
        this.userID = userID;
    }

}