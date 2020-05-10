package com.lijiajie.wynbolg.wynblog.controller;

import com.lijiajie.wynbolg.wynblog.annotation.Log;
import com.lijiajie.wynbolg.wynblog.mapper.UserInfoMapper;
import com.lijiajie.wynbolg.wynblog.mapper.UserManagementMapper;
import com.lijiajie.wynbolg.wynblog.pojo.UserInfo;
import com.lijiajie.wynbolg.wynblog.pojo.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("userinfo")
public class UserInfoController {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserManagementMapper userManagementMapper;

    @Log("获取主人信息")
    @RequestMapping(value = "/getuserinfo", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    @ResponseBody
    public UserManagement getUserInfo(){
        //UserInfo userInfo = userInfoMapper.getUserInfo().get(0);
        UserManagement userManagement = userManagementMapper.getUserInfo(1);
        return userManagement;
    }

    @Log("更换头像")
    @RequestMapping(value = "/updatepicture", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> updatePicture(@RequestBody Map<String,Object> requestMap){
        Map<String,String> resultMap = new HashMap<>();
        userManagementMapper.updatePicture((String)requestMap.get("userPic"),Integer.parseInt((String)requestMap.get("userId")));
        resultMap.put("state","success");
        return resultMap;
    }

    @Log("获取登录人信息")
    @RequestMapping(value = "/getloaduser", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public UserManagement getLoadUser(@RequestBody Map<String,Integer> requestMap){
        UserManagement userManagement = new UserManagement();
        try {
            userManagement = userManagementMapper.getUserInfo(requestMap.get("userId"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return userManagement;
    }

    @Log("更新用户信息")
    @RequestMapping(value = "/updateinfo", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateInfo(@RequestBody Map<String, Object> resquestParams) throws Exception {
        userManagementMapper.updateUserInfo((String)resquestParams.get("phoneNum"), (String)resquestParams.get("userDes"), Integer.parseInt((String)resquestParams.get("userId")));
        Map<String,String> res = new HashMap<>();
        res.put("state","success");
        return res;
    }
}
