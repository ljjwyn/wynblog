package com.lijiajie.wynbolg.wynblog.pojo;

public class UserManagement {
    private int userId;
    private String userName;
    private String userEmail;
    private String password;
    private String phoneNum;
    private String userDes;
    private String userPic;

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getUserDes() {
        return userDes;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setUserDes(String userDes) {
        this.userDes = userDes;
    }

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
