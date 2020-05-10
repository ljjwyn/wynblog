package com.lijiajie.wynbolg.wynblog.mapper;

import com.lijiajie.wynbolg.wynblog.pojo.Authors;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AuthorsMapper {
    @Select("Select * from blog.authors where authorsId=#{authorsId}")
    Authors getAuthor(@Param("authorsId") int authorsId);

    @Select("Select * from blog.authors")
    List<Authors> getAllAuthor();
}
