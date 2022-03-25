package com.how2java.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" }) 
public class FavNews {
	String userName;
    int newsId; //所对应新闻id
    int id;
    public String getUserName() {
        return userName;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
