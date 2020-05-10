package com.lijiajie.wynbolg.wynblog.controller;

import com.lijiajie.wynbolg.wynblog.annotation.Log;
import com.lijiajie.wynbolg.wynblog.config.WebAppConfigurer;
import com.lijiajie.wynbolg.wynblog.mapper.UserInfoMapper;
import com.lijiajie.wynbolg.wynblog.mapper.UserManagementMapper;
import com.lijiajie.wynbolg.wynblog.pojo.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserManagementMapper userManagementMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

//    @GetMapping("/")
//    public String index(@SessionAttribute(WebAppConfigurer.SESSION_KEY) String account, Model model) {
//        model.addAttribute("name", account);
//        return "index.html";
//    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
    @Log("验证登录操作")
    @PostMapping("/loginPost")
    @ResponseBody
    public Map<String, Object> loginPost(String account, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            UserManagement userInfo=userManagementMapper.getUser(account);
            if (!password.equals(userInfo.getPassword())) {
                map.put("success", 0);
                map.put("message", "密码错误");
                return map;
            }else {
                // 设置session
                session.setAttribute(WebAppConfigurer.SESSION_KEY, account);
                map.put("success", 1);
                map.put("message", "登录成功");
                map.put("userId",userInfo.getUserId());
                map.put("userName",userInfo.getUserName());
                return map;
            }
        }catch (Exception e){
            map.put("success", 0);
            map.put("message", "账户不存在");
            return map;
        }

    }
    @Log("注册")
    @RequestMapping(value = "/register", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> register(@RequestBody Map<String, Object> resquestParams) throws Exception {
        UserManagement newUser = new UserManagement();
        newUser.setUserName((String)resquestParams.get("userName"));
        newUser.setUserEmail((String)resquestParams.get("userEmail"));
        newUser.setPassword((String)resquestParams.get("password"));
        Map<String,String> res = new HashMap<>();
        UserManagement isUserNotNull=userManagementMapper.getUser((String)resquestParams.get("userEmail"));
        if(isUserNotNull!=null){
            res.put("state","0");
            res.put("message","用户名已被注册");
        }else {
            userManagementMapper.createAUser(newUser);
            res.put("state","1");
            res.put("message","注册成功");
        }
        return res;
    }

    @Log("获取用户信息")
    @RequestMapping(value = "/getuserinfo", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public UserManagement getUserInfo(@RequestBody Map<String, Integer> resquestParams) throws Exception {
        UserManagement newUser = userManagementMapper.getUserInfo(resquestParams.get("userId"));
        return newUser;
    }

//    @Log("更新用户信息")
//    @RequestMapping(value = "/updateinfo", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, String> updateInfo(@RequestBody Map<String, Object> resquestParams) throws Exception {
//        userManagementMapper.updateUserInfo((String)resquestParams.get("phoneNum"), (String)resquestParams.get("userDes"), Integer.parseInt((String)resquestParams.get("userId")));
//        Map<String,String> res = new HashMap<>();
//        if(Integer.parseInt((String)resquestParams.get("userId"))!=1){
//            System.out.println("不是雅楠，不更新个人描述");
//            res.put("state","not update");
//        }else {
//            userInfoMapper.updateUserDescribe((String)resquestParams.get("userDes"),Integer.parseInt((String)resquestParams.get("userId")));
//            res.put("state","success");
//        }
//        return res;
//    }

    @Log("注销")
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebAppConfigurer.SESSION_KEY);
        return "redirect:login.html";
    }
}
