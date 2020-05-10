package com.lijiajie.wynbolg.wynblog.controller;


import javax.servlet.http.HttpServletRequest;

import com.lijiajie.wynbolg.wynblog.annotation.Log;
import com.lijiajie.wynbolg.wynblog.utils.FilePathUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传
 */
@Controller
@RequestMapping("upload")
public class UploadeController {

    @GetMapping(value = "/file")
    public String file() {
        return "file";
    }

    /**
     * @Describe 图片上传的后端接口前端基于layui实现
     * 需要传一个参数用来区分哪个模块传上来的图。
     * @param file
     * @param flag
     * @return
     */
    @Log("上传图片")
    @PostMapping(value = "/fileupload")
    @ResponseBody
    public Map<String,String> fileUpload(MultipartFile file,@RequestParam("flag") int flag) {
        Map<String,String> resultMap=new HashMap<>();
        if (file.isEmpty()) {
            System.out.println("文件为空空");
            resultMap.put("code","1");
        }else {
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            String filePath;
            if(flag==0){
                 filePath= "/home/jiajie/test/webljj/laopoImage/"; // 上传后的路径
            }else {
                 filePath = "/home/jiajie/test/webljj/laopoImage/user/"; // 上传后的路径
            }
            fileName = UUID.randomUUID().toString().substring(0,5) + suffixName; // 新文件名
            resultMap.put("pictureName",fileName);
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                resultMap.put("code","0");
            } catch (IOException e) {
                resultMap.put("code","2");
                e.printStackTrace();
            }
        }
        return resultMap;
    }

    /**
     * 获取头像文件夹里的照片们
     */
    @Log("上传图片")
    @GetMapping(value = "/getpicturelist")
    @ResponseBody
    public Map<String, List<String>> getPicList(){
        Map<String, List<String>> resultMap = new HashMap<>();
        FilePathUtils filePathUtils=new FilePathUtils();
        resultMap.put("pictureNameList",filePathUtils.getPicturesName());
        return resultMap;
    }

}

