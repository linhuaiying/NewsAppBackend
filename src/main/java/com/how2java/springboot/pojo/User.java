package com.how2java.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" }) 
public class User {
   String userName;
   String passWord;
   String nickName;
   String sex;
   String sign;
   String userIcon;
   
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
   
   public String getNickName() {
	   return this.nickName;
   }
   
   public void setNickName(String nickName) {
	   this.nickName = nickName;
   }
   
   public String getSex() {
	   return this.sex;
   }
   
   public void setSex(String sex) {
	   this.sex = sex;
   }
   
   public String getSign() {
	   return this.sign;
   }
   
   public void setSign(String sign) {
	   this.sign = sign;
   }
   
   public String getUserIcon() {
	   return this.userIcon;
   }
   
   public void setUserIcon(String userIcon) {
	   this.userIcon = userIcon;
   }
   
   @Override
   public String toString() {
       return "User [username=" + userName + ", password=" + passWord + ", nickName=" + nickName + ", sex=" + sex + ", sign=" + sign + ", userIcon=" + userIcon + "]";
   }
}
