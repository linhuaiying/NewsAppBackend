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
import com.how2java.springboot.mapper.NewsContentMapper;
import com.how2java.springboot.mapper.UserMapper;
import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.pojo.NewsContent;
import com.how2java.springboot.pojo.User;

@RestController
public class UserController {
	@Autowired UserMapper userMapper;
	@Autowired NewsContentMapper newsContentMapper;
	
	    @PostMapping("/user/save")
	    public String save(@RequestParam("username") String userName,@RequestParam("password") String passWord) throws Exception {
	    	User user = new User();
	    	user.setUserName(userName);
	    	user.setPassWord(passWord);
	    	int id = userMapper.save(user);
	    	if (id == 1) return "success";
	        return "fail";
	    }
	    
	    //接收新闻内容
	     @PostMapping("/user/uploadNewsContent")
	     public String uploadNewsContent(@RequestParam("newscontent") String content, @RequestParam("username") String userName) {
			NewsContent newsContent = new NewsContent();
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date date = new Date(System.currentTimeMillis());
			System.out.println(formatter.format(date));
			newsContent.setNewsContent(content);
			newsContent.setUserName(userName);
			newsContent.setDate(formatter.format(date));
			int id = newsContentMapper.save(newsContent);
			if (id == 1) return "success";
	        return "fail";
		}
	  	
}
