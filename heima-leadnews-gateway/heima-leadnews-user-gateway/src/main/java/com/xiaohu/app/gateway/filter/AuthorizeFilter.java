package com.xiaohu.app.gateway.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xiaohu.model.user.pojos.ApUser;
import com.xiaohu.utils.common.JwtUtils;
import com.xiaohu.utils.common.Payload;
import com.xiaohu.utils.common.RsaUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.PublicKey;

@Component
public class AuthorizeFilter implements GlobalFilter , Ordered {
    //公钥路径
    @Value("${leadnews.jwt.publicKeyPath}")
    private String publicKeyPath;

    /**
     * 编写过滤器逻辑
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //對登录请求直接放行
        String uri = request.getURI().getPath();
        if (uri.contains("/login")){

            return chain.filter(exchange);
        }

        //1.取出请求头的token
        String token = request.getHeaders().getFirst("token");

        //2.判断token是否存在
        if (StringUtils.isNotEmpty(token)){
            // 如果是null，拒绝访问
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //终止请求
            return response.setComplete();

        }
        //3.检验token是否合法
        try {

            PublicKey publicKey = RsaUtils.getPublicKey(publicKeyPath);

            /**
             * token ：传入的token
             * publicKey ：公钥
             * ApUser：用户信息
             */
            Payload<ApUser> payload = JwtUtils.getInfoFromToken(token, publicKey, ApUser.class);

            ApUser apUser = payload.getInfo();

             request.mutate().header("userId",apUser.getId().toString());

            //放行请求
            return chain.filter(exchange);

        } catch (Exception e) {
            e.printStackTrace();
            //拒绝访问
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //终止请求
            return response.setComplete();
        }



    }
    /**
     * 过滤器执行顺序
     * @return 数值，数值越小，优先级越高
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
