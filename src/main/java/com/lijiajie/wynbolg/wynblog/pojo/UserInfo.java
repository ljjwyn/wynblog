package com.lijiajie.wynbolg.wynblog.pojo;

import org.springframework.stereotype.Component;

@Component
public class UserInfo {
    private int userId;
    private String userName;
    private String userDescribe;
    private String userPic;

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserDescribe() {
        return userDescribe;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserDescribe(String userDescribe) {
        this.userDescribe = userDescribe;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }
}
