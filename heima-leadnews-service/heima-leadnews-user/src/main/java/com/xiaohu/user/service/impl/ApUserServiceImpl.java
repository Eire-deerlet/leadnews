package com.xiaohu.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaohu.common.dtos.AppHttpCodeEnum;
import com.xiaohu.common.dtos.ResponseResult;
import com.xiaohu.common.exception.LeadNewsException;
import com.xiaohu.model.user.dtos.LoginDto;
import com.xiaohu.model.user.pojos.ApUser;
import com.xiaohu.utils.common.BCrypt;
import com.xiaohu.utils.common.JwtUtils;
import com.xiaohu.utils.common.RsaUtils;
import com.xiaohu.user.mapper.ApUserMapper;
import com.xiaohu.user.service.ApUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Violet
 */
@Service
@Transactional
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {

    @Value("${leadnews.jwt.privateKeyPath}")
    private String privateKeyPath;

    @Value("${leadnews.jwt.expire}")
    private Integer expire;

    @Override
    public ResponseResult<Map<String, Object>> login(LoginDto loginDto) {

        //判断是用户登录还是访客登录

        if (StringUtils.isNotEmpty(loginDto.getPhone()) &&
                StringUtils.isNotEmpty(loginDto.getPassword())) {
            //用户登录

            //检验用户名 密码
            QueryWrapper<ApUser> wrapper = new QueryWrapper<>();
            wrapper.eq("phone", loginDto.getPhone());
            //根据手机号拿到数据库的用户名 密码
            ApUser apUser = getOne(wrapper);

            if (apUser == null) {

                // return ResponseResult.errorResult(400, "用户名不存在");
                // throw  new LeadNewsException(400,"用户名不存在");

                throw new LeadNewsException(AppHttpCodeEnum.AP_USER_DATA_NOT_EXIST);

            }

            //传入的密码加密，然后与数据库密码加密进行验证
            if (!BCrypt.checkpw(loginDto.getPassword(), apUser.getPassword())) {

                // return ResponseResult.errorResult(400, "密码错误");

                // throw new LeadNewsException(400, "密码错误");
                throw new LeadNewsException(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }

            //生成 token 传给前端

            loginDto.setPassword("返回前端的token，设置的password");

            try {

                //路径拿到 私钥
                PrivateKey priKey = RsaUtils.getPrivateKey(privateKeyPath);
                String token = JwtUtils.generateTokenExpireInMinutes(apUser, priKey, expire);

                HashMap<String, Object> map = new HashMap<>();

                map.put("token", token);
                map.put("user", apUser);
                return ResponseResult.okResult(map);

            } catch (Exception e) {
                e.printStackTrace();

                //服务器内部错误
                // return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);

                //系统异常
                throw new RuntimeException(e);
            }

        } else {
            //访客登录
            try {

                PrivateKey priKey = RsaUtils.getPrivateKey(privateKeyPath);
                ApUser user = new ApUser();
                //设置访客的ID为0
                user.setId(0);
                String token = JwtUtils.generateTokenExpireInMinutes(user, priKey, expire);

                HashMap<String, Object> result = new HashMap<>();

                result.put("token", token);
                return ResponseResult.okResult(result);


            } catch (Exception e) {
                e.printStackTrace();

                // return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);


                //系统异常
                throw new RuntimeException(e);
            }

        }
    }
}
