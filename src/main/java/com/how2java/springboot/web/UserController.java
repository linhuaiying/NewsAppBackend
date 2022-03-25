package com.how2java.springboot.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.mapper.CategoryMapper;
import com.how2java.springboot.mapper.CommentMapper;
import com.how2java.springboot.mapper.NewsMapper;
import com.how2java.springboot.mapper.UserMapper;
import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.pojo.NewsContent;
import com.how2java.springboot.pojo.User;

@RestController
public class UserController {
	@Autowired UserMapper userMapper;
	@Autowired NewsMapper newsMapper;
	@Autowired CommentMapper commentMapper;
	
	    @PostMapping("/user/save")
	    public String save(@RequestParam("username") String userName,@RequestParam("password") String passWord) throws Exception {
	    	User user = new User();
	    	user.setUserName(userName);
	    	user.setPassWord(passWord);
	    	int id = userMapper.save(user);
	    	if (id == 1) return "success";
	        return "fail";
	    }
	    
	    @PostMapping("/user/update")
	    public String update(@RequestParam("username") String userName,@RequestParam("nickname") String nickName, @RequestParam("sex") String sex, @RequestParam("sign") String sign) throws Exception {
	    	User user = new User();
	    	user.setUserName(userName);
	    	user.setNickName(nickName);
	    	user.setSex(sex);
	    	user.setSign(sign);
	    	int id = userMapper.update(user);
	    	if (id == 1) return "success";
	        return "fail";
	    }
	    
	    @PostMapping("/user/get")
	    public User getUser(@RequestParam("username") String userName) throws Exception {
	    	User user = userMapper.get(userName);
	    	return user;
	    }
	  	
}
