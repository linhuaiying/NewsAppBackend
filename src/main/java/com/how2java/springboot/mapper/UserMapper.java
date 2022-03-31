package com.how2java.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
       
    @Update("update user_ set nickname=#{nickName}, sex=#{sex}, sign=#{sign}, usericon=#{userIcon} where username=#{userName} ")
    public int update(User user);  //更改用户信息
    
    @Select("select nickname from user_ where username= #{userName} ")
    public String getNickName(String userName);
    
    @Select("select usericon from user_ where username= #{userName} ")
    public String getUserIcon(String userName);
    
    @Select("select * from user_ where username= #{userName} ")
    public List<User> getUsersListByUserName(String userName);
    
    @Select("select * from concern_user where username= #{userName} and concern_username=#{keyWords} ")
    public List<User> getConcernUsersListByUserName(@Param("userName") String userName, @Param("keyWords") String keyWords);
    
    @Select("select * from user_ where nickname like concat('%', #{nickName}, '%') ")
    public List<User> getUsersListByNickName(String nickName);
    
    @Select("select * from user_ where username in ( select concern_username from concern_user where username = #{userName} ) and nickname like concat('%', #{nickName}, '%') ")
    public List<User> getConcernUsersListByNickname(@Param("userName") String userName, @Param("nickName") String nickName);
    
    @Select("select * from user_ where username not in ( select concern_username from concern_user where username = #{userName} ) and nickname like concat('%', #{nickName}, '%') ")
    public List<User> getNoConcernUsersListByNickname(@Param("userName") String userName, @Param("nickName") String nickName);
    
    @Insert(" insert into concern_user ( username, concern_username ) values (#{userName}, #{concernUsername}) ")
    public int concernUser(@Param("userName") String userName, @Param("concernUsername") String concernUsername);
    
    @Delete("delete from concern_user where username=#{userName} and concern_username=#{concernUsername} ")
    public int noconcernUser(@Param("userName") String userName, @Param("concernUsername") String concernUsername);
    
    @Select("select * from user_ where username in ( select concern_username from concern_user where username = #{userName} ) ")
    public List<User> getConcernUser(String userName);
    
    @Select("select * from user_ where username in ( select username from concern_user where concern_username = #{userName} ) ")
    public List<User> getFans(String userName);
    
}
