package com.how2java.springboot.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.how2java.springboot.mapper.NewsMapper;
import com.how2java.springboot.mapper.UserMapper;
import com.how2java.springboot.pojo.NewsContent;

@RestController
public class NewsController {
	@Autowired NewsMapper newsMapper;
	@Autowired UserMapper userMapper;
	
	//接收新闻内容
    @PostMapping("/news/sendNewsContent")
    public String uploadNewsContent(@RequestParam("title") String title, @RequestParam("newscontent") String content, @RequestParam("username") String userName) {
		NewsContent newsContent = new NewsContent();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		System.out.println(formatter.format(date));
		String nickName = userMapper.getNickName(userName);
		newsContent.setNewsContent(content);
		newsContent.setUserName(userName);
		newsContent.setDate(formatter.format(date));
		newsContent.setTitle(title);
		newsContent.setNickName(nickName);
		int id = newsMapper.save(newsContent);
		if (id == 1) return "success";
        return "fail";
	}
    
    //返回新闻内容
    @GetMapping("/news/getNewsList")
    public List<NewsContent> getNewsList() {
		List<NewsContent> newsList = newsMapper.findAll();
		return newsList;
	}
    
    //返回个人新闻内容
    @GetMapping("/news/getMyNewsList")
    public List<NewsContent> getMyNewsList(@RequestParam(value = "username", defaultValue = "") String username) {
		List<NewsContent> newsList = newsMapper.getMany(username);
		return newsList;
	}
}
