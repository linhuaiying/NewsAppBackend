package com.how2java.springboot.web;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.mapper.CategoryMapper;
import com.how2java.springboot.pojo.Category;
   
//该注解表示返回数据给前端
@RestController
public class CategoryController {
    @Autowired CategoryMapper categoryMapper;
     
    /*
    @PostMapping("/categories")
    public String listCategory(Category c) throws Exception {
        categoryMapper.save(c);
        return "redirect:/categories";
    }
    @DeleteMapping("/categories/{id}")
    public String deleteCategory(Category c) throws Exception {
        categoryMapper.delete(c.getId());
        return "redirect:/categories";
    }
    @PutMapping("/categories/{id}")
    public String updateCategory(Category c) throws Exception {
        categoryMapper.update(c);
        return "redirect:/categories";
    }
    @GetMapping("/categories/{id}")
    public String listCategory(@PathVariable("id") int id,Model m) throws Exception {
        Category c= categoryMapper.get(id);
        m.addAttribute("c", c);
        return "editCategory";
    }
    前后端不分离*/ 
    
    //前后端分离
     
    @GetMapping("/category")
    public List<Category> listCategory(@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
    	start = start<0?0:start;
    	PageHelper.startPage(start,size,"id desc");
        List<Category> cs=categoryMapper.findAll(); //返回分页后的集合
        PageInfo<Category> page = new PageInfo<>(cs);
        return page.getList();
    }
    
    @PutMapping("/category")
    public void addCategory(@RequestBody Category category) throws Exception {
        System.out.println("springboot接受到浏览器以JSON格式提交的数据："+category); //获取json数据
    }
    
    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable("id") int id) throws Exception {
        Category c= categoryMapper.get(id);
        System.out.println(c);
        return c; //返回json数据
    }
     
}