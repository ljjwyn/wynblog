package com.lijiajie.wynbolg.wynblog.pojo;

import org.springframework.stereotype.Component;

@Component
public class Articles {
    private int articlesId;
    private int articlesAuthorId;
    private String articlesLabel;
    private String articlesTitle;
    private String articlesDate;
    private String articlesDescribe;
    private String articlesContents;
    private int articlesHeartCount;
    private int articlesEyeCount;
    private String authorsName;
    private String authorsPic;
    private String authorsDescribe;
    private String articlesPic;
    private int articlesIsTop;

    public int getArticlesIsTop() {
        return articlesIsTop;
    }

    public void setArticlesIsTop(int articlesIsTop) {
        this.articlesIsTop = articlesIsTop;
    }

    public void setArticlesPic(String articlesPic) {
        this.articlesPic = articlesPic;
    }

    public String getArticlesPic() {
        return articlesPic;
    }

    public void setAuthorsName(String authorsName) {
        this.authorsName = authorsName;
    }

    public void setAuthorsPic(String authorsPic) {
        this.authorsPic = authorsPic;
    }

    public void setAuthorsDescribe(String authorsDescribe) {
        this.authorsDescribe = authorsDescribe;
    }

    public String getAuthorsName() {
        return authorsName;
    }

    public String getAuthorsPic() {
        return authorsPic;
    }

    public String getAuthorsDescribe() {
        return authorsDescribe;
    }

    public void setArticlesId(int articlesId) {
        this.articlesId = articlesId;
    }

    public void setArticlesAuthorId(int articlesAuthorId) {
        this.articlesAuthorId = articlesAuthorId;
    }

    public void setArticlesLabel(String articlesLabel) {
        this.articlesLabel = articlesLabel;
    }

    public void setArticlesTitle(String articlesTitle) {
        this.articlesTitle = articlesTitle;
    }

    public void setArticlesDate(String articlesDate) {
        this.articlesDate = articlesDate;
    }

    public void setArticlesDescribe(String articlesDescribe) {
        this.articlesDescribe = articlesDescribe;
    }

    public void setArticlesContents(String articlesContents) {
        this.articlesContents = articlesContents;
    }

    public void setArticlesHeartCount(int articlesHeartCount) {
        this.articlesHeartCount = articlesHeartCount;
    }

    public void setArticlesEyeCount(int articlesEyeCount) {
        this.articlesEyeCount = articlesEyeCount;
    }

    public int getArticlesId() {
        return articlesId;
    }

    public int getArticlesAuthorId() {
        return articlesAuthorId;
    }

    public String getArticlesLabel() {
        return articlesLabel;
    }

    public String getArticlesTitle() {
        return articlesTitle;
    }

    public String getArticlesDate() {
        return articlesDate;
    }

    public String getArticlesDescribe() {
        return articlesDescribe;
    }

    public String getArticlesContents() {
        return articlesContents;
    }

    public int getArticlesHeartCount() {
        return articlesHeartCount;
    }

    public int getArticlesEyeCount() {
        return articlesEyeCount;
    }
}
