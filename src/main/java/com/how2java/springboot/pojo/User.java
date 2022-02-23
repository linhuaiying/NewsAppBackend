package com.how2java.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" }) 
public class User {
   String userName;
   String passWord;
   
   public String getUserName() {
	   return this.userName;
   }
   
   public void setUserName(String userName) {
	   this.userName = userName;
   }
   
   public String getPassWord() {
	   return this.passWord;
   }
   
   public void setPassWord(String passWord) {
	   this.passWord = passWord;
   }
   
   @Override
   public String toString() {
       return "User [username=" + userName + ", password=" + passWord + "]";
   }
}
