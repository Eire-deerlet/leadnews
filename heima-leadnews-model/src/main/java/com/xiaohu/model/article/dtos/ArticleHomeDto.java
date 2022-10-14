package com.xiaohu.model.article.dtos;

import lombok.Data;

import java.util.Date;

/**
 * @author Violet
 */
@Data
public class ArticleHomeDto {


    /**
     * @param  最大时间
     */
    Date maxBehotTime;

    /**
     * @param   最小时间
     */
    Date minBehotTime;

    /**
     * @param   分页size
     */

    Integer size;
    /**
     * @param   频道ID
     */

    String tag;
}