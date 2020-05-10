package com.lijiajie.wynbolg.wynblog.controller;

import com.lijiajie.wynbolg.wynblog.annotation.CheckName;
import com.lijiajie.wynbolg.wynblog.annotation.Log;
import com.lijiajie.wynbolg.wynblog.mapper.ActionLogMapper;
import com.lijiajie.wynbolg.wynblog.pojo.ActionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * @author jiajie
 * @describe 切面日志管理控制器，存储操作和获取日志
 */
@Controller
@RequestMapping("log")
public class ActionLogController {
    @Autowired
    private ActionLogMapper actionLogMapper;

    @Log("测试log")
    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        System.out.println(1111);
        return "success";
    }

    @Log("获取日志")
    @RequestMapping(value = "/getalllog", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    @ResponseBody
    public List<ActionLog> getAllLog(){
        List<ActionLog> actionLogList = actionLogMapper.getAllLog();
        return actionLogList;
    }
}
