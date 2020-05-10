package com.lijiajie.wynbolg.wynblog.mapper;


import com.lijiajie.wynbolg.wynblog.pojo.ActionLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ActionLogMapper {
    @Insert("INSERT IGNORE into blog.actionLog(logAction, logError, logDate) " +
            "VALUES (#{ActionLog.logAction}, #{ActionLog.logError}, #{ActionLog.logDate})")
    void insertLog(@Param("ActionLog") ActionLog actionLog);

    @Select("Select * from blog.actionLog")
    List<ActionLog> getAllLog();
}
