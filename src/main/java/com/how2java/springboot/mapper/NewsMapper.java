package com.how2java.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.how2java.springboot.pojo.NewsContent;
import com.how2java.springboot.pojo.User;

//用于数据库增删改查
@Mapper
public interface NewsMapper {
	
	@Select("select * from news_content order by date desc")
    List<NewsContent> findAll();
     
    @Insert(" insert into news_content ( username, nickname, title, newscontent, date, usericon ) values (#{userName}, #{nickName}, #{title}, #{newsContent}, #{date}, #{userIcon}) ")
    public int save(NewsContent newsContent); 
     
    @Delete(" delete from news_content where id= #{id} ")
    public void delete(int id);
         
    @Select("select * from news_content where id= #{id} ")
    public NewsContent get(String id); //获取某一条文章
    
    @Select("select * from news_content where username= #{userName} order by date desc")
    public List<NewsContent> getMany(String userName); //获取该用户所有的文章
       
    @Update("update news_content set title=#{title}, newscontent=#{newsContent}, date=#{date}, usericon=#{userIcon} where id=#{id} ")
    public int update(NewsContent newsContent); 

    @Select("select * from news_content where id in ( select newsId from fav_news where username = #{userName} ) ")
    public List<NewsContent> getFavNews(String userName);
    
    @Select("select * from news_content where title like concat('%', #{keyWord}, '%') ")
    public List<NewsContent> getSearchNews(String keyWords);
}
