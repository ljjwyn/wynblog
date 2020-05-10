package com.lijiajie.wynbolg.wynblog.controller;

import com.lijiajie.wynbolg.wynblog.annotation.Log;
import com.lijiajie.wynbolg.wynblog.mapper.ArticlesMapper;
import com.lijiajie.wynbolg.wynblog.mapper.AuthorsMapper;
import com.lijiajie.wynbolg.wynblog.pojo.Articles;
import com.lijiajie.wynbolg.wynblog.pojo.Authors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    ArticlesMapper articlesMapper;

    @Autowired
    AuthorsMapper authorsMapper;
    @Log("获取所有文章")
    @RequestMapping(value = "/getarticlelist", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Articles> getArticlesInfo(){
        List<Articles> resultArticlesList = articlesMapper.getArticles();
        for(Articles articles:resultArticlesList){
            Authors tempAuthor = authorsMapper.getAuthor(articles.getArticlesAuthorId());
            articles.setAuthorsName(tempAuthor.getAuthorsName());
            articles.setAuthorsDescribe(tempAuthor.getAuthorsDescribe());
            articles.setAuthorsPic(tempAuthor.getAuthorsPic());
        }
        return resultArticlesList;
    }
    @Log("获取文章详情")
    @RequestMapping(value = "/getaarticle", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public Articles getAArticleInfo(@RequestBody Map<String, Integer> resquestParams){
        Articles article = articlesMapper.getAArticle(resquestParams.get("articleId"));
        Authors tempAuthor = authorsMapper.getAuthor(article.getArticlesAuthorId());
        article.setAuthorsName(tempAuthor.getAuthorsName());
        article.setAuthorsDescribe(tempAuthor.getAuthorsDescribe());
        article.setAuthorsPic(tempAuthor.getAuthorsPic());
        return article;
    }
    @Log("获取置顶文章")
    @RequestMapping(value = "/gettoparticle", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    @ResponseBody
    public Articles getTopArticle(){
        Articles article = articlesMapper.getTopArticle();
        Authors tempAuthor = authorsMapper.getAuthor(article.getArticlesAuthorId());
        article.setAuthorsName(tempAuthor.getAuthorsName());
        article.setAuthorsDescribe(tempAuthor.getAuthorsDescribe());
        article.setAuthorsPic(tempAuthor.getAuthorsPic());
        return article;
    }

    /**
     * @Describe:更新访问次数，这里有线程安全的问题需要解决
     */
    @Log("更新文章访问次数")
    @RequestMapping(value = "/updateeyecount", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public void updateEyeCount(@RequestBody Map<String,Integer> resquestParams){
        int count = articlesMapper.getEyeCount(resquestParams.get("articleId"));
        count++;
        articlesMapper.updateArticleEyeCount(count,resquestParams.get("articleId"));

    }

    /**
     * @Describe 更新收藏次数
     * @param resquestParams
     */
    @Log("更新文章收藏次数")
    @RequestMapping(value = "/updateheartcount", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Integer> updateHeartCount(@RequestBody Map<String,Integer> resquestParams){
        int count = articlesMapper.getHeartCount(resquestParams.get("articleId"));
        count++;
        System.out.println(resquestParams.get("articleId"));
        articlesMapper.updateArticleHeartCount(count,resquestParams.get("articleId"));
        Map<String,Integer> resultMap = new HashMap<>();
        resultMap.put("newHeartCount",count);
        return resultMap;
    }

    @Log("修改文章")
    @RequestMapping(value = "/updatearticle", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> updateArticle(@RequestBody Map<String, Object> resquestParams){
        Map<String,String> resultMap = new HashMap<>();
        int articlesId = (Integer)resquestParams.get("articlesId");
        String articlesLabel = (String)resquestParams.get("articlesLabel");
        String articlesTitle = (String)resquestParams.get("articlesTitle");
        String articlesDescribe = (String)resquestParams.get("articlesDescribe");
        String articlesContents = (String)resquestParams.get("articlesContents");
        int articlesIsTop = (Integer)resquestParams.get("articlesIsTop");
        if(articlesIsTop==1){
            resultMap.put("isTop","更换/设置最佳文章");
            Articles topArticle = articlesMapper.getTopArticle();
            if(topArticle.getArticlesId()>0){
                articlesMapper.updateTopArticle(0,topArticle.getArticlesId());
            }
        }else {
            resultMap.put("isTop","未更换最佳文章");
        }
        articlesMapper.updateArticle(articlesLabel,articlesTitle,articlesDescribe,articlesContents,articlesIsTop,articlesId);
        resultMap.put("state","更新完成");
        return resultMap;
    }

    @Log("插入文章")
    @RequestMapping(value = "/insertarticle", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> insertArticle(@RequestBody Map<String, Object> resquestParams){
        Map<String,String> resultMap = new HashMap<>();
        Articles articles = new Articles();
        articles.setArticlesLabel((String)resquestParams.get("articlesLabel"));
        articles.setArticlesTitle((String)resquestParams.get("articlesTitle"));
        articles.setArticlesDescribe((String)resquestParams.get("articlesDescribe"));
        articles.setArticlesContents((String)resquestParams.get("articlesContents"));
        articles.setArticlesPic("Path/"+resquestParams.get("articlesPic"));
        articles.setArticlesDate((String)resquestParams.get("articlesDate"));
        articles.setArticlesAuthorId((Integer)resquestParams.get("articlesAuthorId"));
        int articlesIsTop = (Integer)resquestParams.get("articlesIsTop");
        articles.setArticlesIsTop(articlesIsTop);
        articles.setArticlesEyeCount(0);
        articles.setArticlesHeartCount(0);
        if(articlesIsTop==1){
            resultMap.put("isTop","更换/设置最佳文章");
            Articles topArticle = articlesMapper.getTopArticle();
            if(topArticle.getArticlesId()>0){
                articlesMapper.updateTopArticle(0,topArticle.getArticlesId());
            }
        }else {
            resultMap.put("isTop","未更换最佳文章");
        }
        articlesMapper.createArticle(articles);
        resultMap.put("state","新文章记录完成");
        return resultMap;
    }
}
