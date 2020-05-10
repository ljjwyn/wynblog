package com.lijiajie.wynbolg.wynblog.pojo;

import org.springframework.stereotype.Component;

@Component
public class Authors {
    private int authorsId;
    private String authorsName;
    private String authorsDescribe;
    private String authorsPic;

    public void setAuthorsId(int authorsId) {
        this.authorsId = authorsId;
    }

    public void setAuthorsName(String authorsName) {
        this.authorsName = authorsName;
    }

    public void setAuthorsDescribe(String authorsDescribe) {
        this.authorsDescribe = authorsDescribe;
    }

    public void setAuthorsPic(String authorsPic) {
        this.authorsPic = authorsPic;
    }

    public int getAuthorsId() {
        return authorsId;
    }

    public String getAuthorsName() {
        return authorsName;
    }

    public String getAuthorsDescribe() {
        return authorsDescribe;
    }

    public String getAuthorsPic() {
        return authorsPic;
    }
}
