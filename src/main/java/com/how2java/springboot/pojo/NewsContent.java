package com.how2java.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" }) 
public class NewsContent {
    String userName;
    String newsContent;
    String date;
    int id;
    
    public String getUserName() {
 	   return this.userName;
    }
    
    public void setUserName(String userName) {
 	   this.userName = userName;
    }
    
    public String getNewsContent() {
 	   return this.newsContent;
    }
    
    public void setNewsContent(String newsContent) {
 	   this.newsContent = newsContent;
    }
    
    public String getDate() {
 	   return this.date;
    }
    
    public void setDate(String date) {
 	   this.date = date;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "NewsContent [username=" + userName + ", newsContent=" + newsContent + ", date =" + date + "]";
    }
}
