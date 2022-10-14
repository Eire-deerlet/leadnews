package com.xiaohu.user.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 扫描全局异常处理类
 * @author Violet
 */
@Configuration
@ComponentScan("com.xiaohu.common.exception")
public class ExceptionConfig {
}
