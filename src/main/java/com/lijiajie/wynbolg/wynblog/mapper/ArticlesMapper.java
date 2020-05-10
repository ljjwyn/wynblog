package com.lijiajie.wynbolg.wynblog.mapper;

import com.lijiajie.wynbolg.wynblog.pojo.Articles;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ArticlesMapper {
    @Select("Select * from blog.articles")
    List<Articles> getArticles();

    @Select("Select * from blog.articles where articlesId = #{articlesId}")
    Articles getAArticle(@Param("articlesId") int articleId);

    @Select("Select * from blog.articles where articlesIsTop = 1")
    Articles getTopArticle();

    @Select("Select articlesEyeCount from blog.articles where articlesId = #{articlesId}")
    int getEyeCount(@Param("articlesId") int articlesId);

    @Update("UPDATE blog.articles SET articlesEyeCount=#{articlesEyeCount}" +
            " WHERE articlesId = #{articlesId}")
    void updateArticleEyeCount(@Param("articlesEyeCount") int articlesEyeCount, @Param("articlesId") int articlesId);

    @Select("Select articlesHeartCount from blog.articles where articlesId = #{articlesId}")
    int getHeartCount(@Param("articlesId") int articlesId);

    @Update("UPDATE blog.articles SET articlesHeartCount=#{articlesHeartCount}" +
            " WHERE articlesId = #{articlesId}")
    void updateArticleHeartCount(@Param("articlesHeartCount") int articlesHeartCount, @Param("articlesId") int articlesId);

    @Delete("DELETE FROM blog.articles WHERE articlesId = #{articlesId}")
    void deleteArticle(@Param("articlesId") int articlesId);

    @Update("UPDATE blog.articles SET articlesLabel=#{articlesLabel}, articlesTitle=#{articlesTitle}" +
            ", articlesDescribe=#{articlesDescribe}, articlesContents=#{articlesContents}, articlesIsTop=#{articlesIsTop}" +
            " WHERE articlesId = #{articlesId}")
    void updateArticle(@Param("articlesLabel") String articlesLabel, @Param("articlesTitle") String articlesTitle
            , @Param("articlesDescribe") String articlesDescribe, @Param("articlesContents") String articlesContents
            , @Param("articlesIsTop") int articlesIsTop, @Param("articlesId") int articlesId);

    @Update("UPDATE blog.articles SET articlesIsTop=#{articlesIsTop} WHERE articlesId = #{articlesId}")
    void updateTopArticle(@Param("articlesIsTop") int articlesIsTop, @Param("articlesId") int articlesId);

    @Insert("INSERT IGNORE into blog.articles(articlesAuthorId, articlesLabel, articlesTitle, articlesDate, " +
            "articlesDescribe, articlesContents, articlesPic, articlesIsTop, articlesHeartCount, articlesEyeCount) " +
            "VALUES (#{articles.articlesAuthorId}, #{articles.articlesLabel}, #{articles.articlesTitle}" +
            ", #{articles.articlesDate}, #{articles.articlesDescribe}, #{articles.articlesContents}" +
            ", #{articles.articlesPic}, #{articles.articlesIsTop}, #{articles.articlesIsTop}, #{articles.articlesIsTop})")
    void createArticle(@Param("articles") Articles articles);
}
