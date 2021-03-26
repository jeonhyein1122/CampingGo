package com.hi1122.campinggo;

public class Chatting_MessageItem {

    public String name;
    public String message;
    public String visualtime;
    public String profileUrl;

    public Chatting_MessageItem() {
    }

    public Chatting_MessageItem(String name, String message, String visualtime, String profileUrl) {
        this.name = name;
        this.message = message;
        this.visualtime = visualtime;
        this.profileUrl = profileUrl;
    }
}