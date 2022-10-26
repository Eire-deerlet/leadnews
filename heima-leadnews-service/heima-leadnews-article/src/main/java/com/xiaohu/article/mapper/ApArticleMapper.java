package com.xiaohu.article.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaohu.model.article.dtos.ArticleHomeDto;
import com.xiaohu.model.article.pojos.ApArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Violet
 */
public interface ApArticleMapper extends BaseMapper<ApArticle> {


    List<ApArticle> loadArticles(@Param("dto") ArticleHomeDto dto, @Param("type") int type);
}