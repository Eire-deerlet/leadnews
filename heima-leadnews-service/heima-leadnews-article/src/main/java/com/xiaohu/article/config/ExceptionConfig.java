package com.xiaohu.article.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 异常配置：用于扫描全局异常处理类
 */
@Configuration
@ComponentScan("com.xiaohu.common.exception")
public class ExceptionConfig {
}
