package com.how2java.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.how2java.springboot.pojo.Comment;
import com.how2java.springboot.pojo.User;

//用于数据库增删改查
@Mapper
public interface CommentMapper {

	@Select("select * from comment where newsId = #{newsId}")
    List<Comment> getMany(int newsId);
     
    @Insert(" insert into comment ( username, nickname, content, newsId, usericon ) values (#{userName}, #{nickName}, #{content}, #{newsId}, #{userIcon}) ")
    public int save(Comment comment); 
    
    @Select("select * from comment")
    List<Comment> findAll();
    
    @Delete(" delete from comment where id= #{id} ")
    public void delete(int id);
    
}
