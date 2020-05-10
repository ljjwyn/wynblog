package com.lijiajie.wynbolg.wynblog.controller;


import com.lijiajie.wynbolg.wynblog.annotation.Log;
import com.lijiajie.wynbolg.wynblog.mapper.AuthorsMapper;
import com.lijiajie.wynbolg.wynblog.pojo.Authors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorsMapper authorsMapper;

    @Log("获取作者信息")
    @RequestMapping(value = "/getaauthor", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public Authors getAAuthorInfo(@RequestBody Map<String, Integer> resquestParams){
        Authors authors = authorsMapper.getAuthor(resquestParams.get("authorsId"));
        return authors;
    }

    @Log("获取所有的作者信息")
    @RequestMapping(value = "/getallauthor", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Authors> getAAuthorInfo(){
        List<Authors> authors = authorsMapper.getAllAuthor();
        return authors;
    }
}
