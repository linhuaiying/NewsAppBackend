package com.how2java.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" }) 
public class NewsContent {
    String userName;
    String newsContent;
    String date;
    String title;
    String nickName;
    String userIcon;
    int id;
    
    public String getUserName() {
 	   return this.userName;
    }
    
    public void setUserName(String userName) {
 	   this.userName = userName;
    }
    
    public String getNickName() {
  	   return this.nickName;
    }
     
    public void setNickName(String nickName) {
  	   this.nickName = nickName;
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
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getUserIcon() {
  	   return this.userIcon;
    }
     
    public void setUserIcon(String userIcon) {
  	   this.userIcon = userIcon;
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
