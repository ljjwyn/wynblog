package com.lijiajie.wynbolg.wynblog.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilePathUtils {
    public List<String> getPicturesName(){
        List<String> resultList = new ArrayList<>();
        File file=new File("/home/jiajie/test/webljj/laopoImage/user/");
        String[] fileName = file.list();
        for (int i = 0; i < fileName.length; i++) {
            resultList.add("Path/user/"+fileName[i]);
        }
        return resultList;
    }
}
