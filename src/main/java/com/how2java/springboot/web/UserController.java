package com.how2java.springboot.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.how2java.springboot.mapper.CommentMapper;
import com.how2java.springboot.mapper.NewsMapper;
import com.how2java.springboot.mapper.UserMapper;
import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.pojo.NewsContent;
import com.how2java.springboot.pojo.User;

@RestController
public class UserController {
	@Autowired UserMapper userMapper;
	@Autowired NewsMapper newsMapper;
	@Autowired CommentMapper commentMapper;
	
	    @PostMapping("/user/save")
	    public String save(@RequestParam("username") String userName,@RequestParam("password") String passWord) throws Exception {
	    	User user = new User();
	    	user.setUserName(userName);
	    	user.setPassWord(passWord);
	    	int id = userMapper.save(user);
	    	if (id == 1) return "success";
	        return "fail";
	    }
	    
	    @PostMapping("/user/update")
	    public String update(@RequestParam("username") String userName,@RequestParam("nickname") String nickName, @RequestParam("sex") String sex, @RequestParam("sign") String sign, @RequestParam("userIcon") String userIcon) throws Exception {
	    	User user = new User();
	    	user.setUserName(userName);
	    	user.setNickName(nickName);
	    	user.setSex(sex);
	    	user.setSign(sign);
	    	if(!userIcon.equals("")) user.setUserIcon(userIcon);
	    	int id = userMapper.update(user);
	    	if (id == 1) return "success";
	        return "fail";
	    }
	    
	    @PostMapping("/user/get")
	    public User getUser(@RequestParam("username") String userName) throws Exception {
	    	User user = userMapper.get(userName);
	    	return user;
	    }
	    
	    @PostMapping("/user/delete")
	    public void deleteUser(@RequestParam("username") String userName) throws Exception {
	    	userMapper.delete(userName);
	    }
	    
	    @GetMapping("/user/getUserList")
	    public List<User> getUsers() throws Exception {
	    	List<User> users = userMapper.findAll();
	    	return users;
	    }
	    
	    @PostMapping("/user/getUsersListByUserName")
	    public List<User> getUsersListByUserName(@RequestParam("username") String userName) throws Exception {
	    	List<User> users = userMapper.getUsersListByUserName(userName);
	    	return users;
	    }
	    
	    @PostMapping("/user/getUsersListByNickName")
	    public List<User> getUsersListByNickName(@RequestParam("keyWords") String keyWords) throws Exception {
	    	List<User> users = userMapper.getUsersListByNickName(keyWords);
	    	return users;
	    }
	    
	    @PostMapping("/user/getUsers")
	    public Map<String, List<User>> getUserList(@RequestParam("userName") String userName, @RequestParam("keyWords") String keyWords) throws Exception {
	    	List<User> concernUsers = null;
	    	List<User> noconcernUsers = null;
	    	List<User> users = null;
	    	Map<String, List<User>> map = new HashMap<String, List<User>>();
	    	if(judgePhoneNums(keyWords)) {
	    		users = userMapper.getUsersListByUserName(keyWords);
	    		if(users != null && users.size() > 0) {
	    			//如果该用户存在被关注列表里
		    		if(userMapper.getConcernUsersListByUserName(userName, users.get(0).getUserName()).size() > 0) {
		    			map.put("concern", users);
		    		} else {
						map.put("noconcern", users);
					}
	    		} else {
	    			map.put("concern", null);
	    			map.put("noconcern", null);
	    		}
	    	}
	    	else {
	    		concernUsers = userMapper.getConcernUsersListByNickname(userName, keyWords);
	    		noconcernUsers = userMapper.getNoConcernUsersListByNickname(userName, keyWords);
	    		map.put("concern", concernUsers);
	    		map.put("noconcern", noconcernUsers);
	    	}
	    	return map;
	    }
	    
	    @PostMapping("/user/getMyFans")
	    public Map<String, List<User>> getFans(@RequestParam("username") String userName) throws Exception {
	    	List<User> concernUsers = new ArrayList<User>();
	    	List<User> concernFans = new ArrayList<User>();
	    	List<User> noconcernFans = new ArrayList<User>();
	    	List<User> fans = new ArrayList<User>();
	    	Map<String, List<User>> map = new HashMap<String, List<User>>();
	    	boolean flag = false;
	    	fans = userMapper.getFans(userName); //用户的全部粉丝
	    	concernUsers = userMapper.getConcernUser(userName); //用户的全部关注
            for(int i = 0; i < fans.size(); i++) {
            	flag = false;
            	for(int j = 0; j < concernUsers.size(); j++) {
            		if(fans.get(i).getUserName().equals(concernUsers.get(j).getUserName())) {
            			concernFans.add(fans.get(i));
            			flag = true;
            			break;
            		}
            	}
            	if(!flag) noconcernFans.add(fans.get(i));
            }
	    	map.put("concern", concernFans);
	    	map.put("noconcern", noconcernFans);
	    	return map;
	    }
	    
	    @PostMapping("/user/concern")
	    public String concernUser(@RequestParam("username") String userName, @RequestParam("concernUsername") String concernUsername) throws Exception {
	    	int id = userMapper.concernUser(userName, concernUsername);
	    	if(id > 0) return "success";
	    	return "fail";
	    }
	    
	    @PostMapping("/user/noconcern")
	    public String noconcernUser(@RequestParam("username") String userName, @RequestParam("concernUsername") String concernUsername) throws Exception {
	    	int id = userMapper.noconcernUser(userName, concernUsername);
	    	if(id > 0) return "success";
	    	return "fail";
	    }
	    
	    @PostMapping("/user/getConcernUser")
	    public List<User> concernUser(@RequestParam("username") String userName) throws Exception {
	    	List<User> users = userMapper.getConcernUser(userName);
	    	return users;
	    }
	    
	    private boolean judgePhoneNums(String phoneNums) {
	        if (isMatchLength(phoneNums, 11)
	                && isMobileNO(phoneNums)) {
	            return true;
	        }
	        return false;
	    }

	    public static boolean isMatchLength(String str, int length) {
	        if (str.isEmpty()) {
	            return false;
	        } else {
	            return str.length() == length ? true : false;
	        }
	    }

	    public static boolean isMobileNO(String mobileNums) {
	        /*
	         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
	         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
	         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
	         */
	        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
	        if (mobileNums.isEmpty())
	            return false;
	        else
	            return mobileNums.matches(telRegex);
	    }
	  
}
