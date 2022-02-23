package com.how2java.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.pojo.User;

//用于数据库增删改查
@Mapper
public interface UserMapper {
	
	@Select("select * from user_ ")
    List<User> findAll();
     
    @Insert(" insert into user_ ( username, password ) values (#{userName}, #{passWord}) ")
    public int save(User user); 
     
    @Delete(" delete from user_ where username= #{userName} ")
    public void delete(String userName);
         
    @Select("select * from user_ where username= #{userName} ")
    public User get(String userName);
       
    @Update("update user_ set username=#{userName} where password=#{passWord} ")
    public int update(User user); 
    
}
