package com.how2java.springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.how2java.springboot.mapper.FavNewsMapper;
import com.how2java.springboot.pojo.FavNews;

@RestController
public class FavNewsController {
	@Autowired FavNewsMapper favNewsMapper;
	
	@PostMapping("fav")
	public String addFav(@RequestParam("username") String userName, @RequestParam("newsId") int newsId) {
		FavNews favNews = new FavNews();
		favNews.setUserName(userName);
		favNews.setNewsId(newsId);
		int id = favNewsMapper.save(favNews);
		if(id > 0) return "success";
		return "fail";
	}
	
	@PostMapping("deletefav")
	public String deleteFav(@RequestParam("username") String userName, @RequestParam("newsId") int newsId) {
		FavNews favNews = new FavNews();
		favNews.setUserName(userName);
		favNews.setNewsId(newsId);
		favNewsMapper.deleteFav(favNews);
		return "success";
	}
	
	@PostMapping("getfav")
	public String getFavNews(@RequestParam("username") String userName, @RequestParam("newsId") int newsId) {
		FavNews favNews = new FavNews();
		favNews.setUserName(userName);
		favNews.setNewsId(newsId);
		if(favNewsMapper.getFavNews(favNews) != null) return "success";
		else return "fail";
	}
}
