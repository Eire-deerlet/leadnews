package com.xiaohu.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaohu.article.mapper.ApArticleMapper;
import com.xiaohu.article.service.ApArticleService;
import com.xiaohu.model.user.pojos.ApArticle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Violet
 */
@Service
@Transactional
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {
}
