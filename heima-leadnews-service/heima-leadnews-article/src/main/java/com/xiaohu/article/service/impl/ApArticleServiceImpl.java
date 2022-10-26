package com.xiaohu.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaohu.article.mapper.ApArticleMapper;
import com.xiaohu.article.service.ApArticleService;
import com.xiaohu.common.dtos.ResponseResult;
import com.xiaohu.model.article.dtos.ArticleHomeDto;
import com.xiaohu.model.article.pojos.ApArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Violet
 */
@Service
@Transactional
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {
    @Autowired
    private ApArticleMapper apArticleMapper;

    @Override
    public ResponseResult<List<ApArticle>> loadArticles(ArticleHomeDto dto, int type) {
        if(dto.getMaxBehotTime() ==null){
        dto.setMaxBehotTime(new Date());
        }
        if (dto.getMinBehotTime()==null){
            dto.setMinBehotTime(new Date());
        }
        if(dto.getSize()==null){dto.setSize(10);}

        List<ApArticle> apArticles= apArticleMapper.loadArticles(dto,type);

        return  ResponseResult.okResult(apArticles);

    }
}
