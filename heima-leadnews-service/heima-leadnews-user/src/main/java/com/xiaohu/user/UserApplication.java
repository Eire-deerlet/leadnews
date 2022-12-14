package com.xiaohu.user;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @author Violet
 */
@SpringBootApplication
@MapperScan("com.xiaohu.user.mapper")
@EnableDiscoveryClient
public class UserApplication {
     public static void main(String[] args) {

         SpringApplication.run(UserApplication.class,args);
    }

    /**
     *  分页拦截器
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){

         return new PaginationInterceptor();
    }
}
