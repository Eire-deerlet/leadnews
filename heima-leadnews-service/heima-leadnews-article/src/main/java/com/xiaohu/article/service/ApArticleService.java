package com.xiaohu.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaohu.common.dtos.ResponseResult;
import com.xiaohu.model.article.dtos.ArticleHomeDto;
import com.xiaohu.model.article.pojos.ApArticle;

import java.util.List;

/**
 * @author Violet
 */
public interface ApArticleService extends IService<ApArticle> {
    ResponseResult<List<ApArticle>> loadArticles(ArticleHomeDto dto, int i);
}
