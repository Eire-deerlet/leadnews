package com.xiaohu.article.controller;

import com.xiaohu.article.service.ApArticleService;
import com.xiaohu.common.dtos.ResponseResult;
import com.xiaohu.model.article.dtos.ArticleHomeDto;
import com.xiaohu.model.article.pojos.ApArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 加载文章首页数据
 */
@RestController
@RequestMapping("/api/v1/article")
public class ArticleHomeController {

    @Autowired
    private ApArticleService apArticleService;

    /**
     * 第一頁數據
     */
    @PostMapping("/load")
    public ResponseResult<List<ApArticle>> load(@RequestBody ArticleHomeDto dto){
        // type : 1 查詢发布时间<minBeHotTime
        return apArticleService.loadArticles(dto,1);

    }

    /**
     *更多数据
     */
    @PostMapping("/loadmore")
    public ResponseResult<List<ApArticle>> loadmore(@RequestBody ArticleHomeDto dto){
        // type : 1 查詢发布时间<minBeHotTime
        return apArticleService.loadArticles(dto,1);

    }


    /**
     *更新数据
     */
    @PostMapping("/loadnew")
    public ResponseResult<List<ApArticle>> loadnew(@RequestBody ArticleHomeDto dto){
        // type : 1 查詢发布时间>maxBeHotTime
        return apArticleService.loadArticles(dto,2);

    }





}
