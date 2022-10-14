package com.xiaohu.user.controller.v1;

import com.xiaohu.common.dtos.ResponseResult;
import com.xiaohu.model.user.dtos.LoginDto;
import com.xiaohu.user.service.ApUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Violet
 */
@RestController
@RequestMapping("/api/v1/login")
public class ApUserController {

    @Autowired
    private ApUserService apUserService;


    /**
     * 用户登录
     */
    @PostMapping("/login_auth")
    public ResponseResult<Map<String, Object>> login(@RequestBody LoginDto loginDto) {

        return apUserService.login(loginDto);

    }

}
