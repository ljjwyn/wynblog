package com.lijiajie.wynbolg.wynblog.mapper;

import com.lijiajie.wynbolg.wynblog.pojo.UserManagement;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserManagementMapper {
    @Select("SELECT * FROM blog.userManagement")
    List<UserManagement> getAllUser();

    @Select("SELECT * FROM blog.userManagement WHERE userEmail=#{userEmail}")
    UserManagement getUser(@Param("userEmail") String userEmail);

    @Select("SELECT * FROM blog.userManagement WHERE userId=#{userId}")
    UserManagement getUserInfo(@Param("userId") int userId);

    @Insert("INSERT IGNORE into blog.userManagement(userName, userEmail, password) " +
            "VALUES (#{UserManagement.userName}, #{UserManagement.userEmail},#{UserManagement.password})")
    void createAUser(@Param("UserManagement") UserManagement usermanagement);

    @Delete("DELETE FROM blog.userManagement WHERE userId = #{userId}")
    void deleteUser(@Param("userId") int userId);

    @Update("UPDATE blog.userManagement SET phoneNum=#{phoneNum}, userDes=#{userDes} WHERE userId=#{userId}")
    void updateUserInfo(@Param("phoneNum") String phoneNum, @Param("userDes") String userDes, @Param("userId") int userId);

    @Update("UPDATE blog.userManagement SET userPic=#{userPic} WHERE userId=#{userId}")
    void updatePicture(@Param("userPic") String userPic, @Param("userId") int userId);
}
