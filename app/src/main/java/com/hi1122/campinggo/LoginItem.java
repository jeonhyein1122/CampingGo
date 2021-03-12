package com.hi1122.campinggo;

public class LoginItem {
    int no;
    String userID;
    String userPassword;
    String userName;
    String file;

    public LoginItem() {
    }

    public LoginItem(int no,String userID, String userPassword, String userName, String file) {
        this.no=no;
        this.userID = userID;
        this.userPassword = userPassword;
        this.userName = userName;
        this.file = file;
    }
}
