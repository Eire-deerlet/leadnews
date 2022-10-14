package com.xiaohu.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaohu.common.dtos.ResponseResult;
import com.xiaohu.model.user.dtos.LoginDto;
import com.xiaohu.model.user.pojos.ApUser;

import java.util.Map;

/**
 * @author Violet
 */
public interface ApUserService extends IService<ApUser> {

    ResponseResult<Map<String, Object>> login(LoginDto loginDto);
}
