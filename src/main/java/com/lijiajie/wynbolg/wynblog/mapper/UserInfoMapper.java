package com.lijiajie.wynbolg.wynblog.mapper;

import com.lijiajie.wynbolg.wynblog.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component

public interface UserInfoMapper {
    @Select("SELECT userId,userName,userDescribe,userPic FROM blog.userInfo")
    List<UserInfo> getUserInfo();

    @Update("UPDATE blog.userInfo SET userDescribe=#{userDescribe} WHERE userId=#{userId}")
    void updateUserDescribe(@Param("userDescribe") String userDescribe, @Param("userId") int userId);
}
