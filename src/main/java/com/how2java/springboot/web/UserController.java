package com.how2java.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.mapper.CategoryMapper;
import com.how2java.springboot.mapper.UserMapper;
import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.pojo.User;

@RestController
public class UserController {
	@Autowired UserMapper userMapper;
	
	    @PostMapping("/user/save")
	    public String save(@RequestParam("username") String userName,@RequestParam("password") String passWord) throws Exception {
	    	User user = new User();
	    	user.setUserName(userName);
	    	user.setPassWord(passWord);
	    	int id = userMapper.save(user);
	    	if(id == 1) return "success";
	        return "fail";
	    }
}
