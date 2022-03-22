package com.how2java.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.how2java.springboot.mapper.CommentMapper;
import com.how2java.springboot.pojo.Comment;

@RestController
public class CommentController {
	@Autowired CommentMapper commentMapper;
	
    @PostMapping("comment/sendComment")
    public String addComment(@RequestBody Comment comment) throws Exception {
        int id = commentMapper.save(comment);
        if (id == 1) return "success";
        return "fail";
    }
    
    @PostMapping("comment/getComments")
    public List<Comment> getComments(@RequestParam(value = "newsId", defaultValue = "") int newsId) {
		List<Comment> comments = commentMapper.getMany(newsId);
		return comments;
	}
    
}
