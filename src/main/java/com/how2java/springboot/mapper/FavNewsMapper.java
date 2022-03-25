package com.how2java.springboot.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.how2java.springboot.pojo.FavNews;

//用于数据库增删改查
@Mapper
public interface FavNewsMapper {
	
    @Insert(" insert into fav_news ( username, newsId ) values (#{userName}, #{newsId}) ")
    public int save(FavNews favNews);
    
    @Delete(" delete from fav_news where username = #{userName} and newsId = #{newsId} ")
    public void deleteFav(FavNews favNews);
    
    @Select("select * from fav_news where username = #{userName} and newsId = #{newsId} ")
    public FavNews getFavNews(FavNews favNews);
}
