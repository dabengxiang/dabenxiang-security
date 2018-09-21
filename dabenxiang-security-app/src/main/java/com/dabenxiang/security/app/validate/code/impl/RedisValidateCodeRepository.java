package com.dabenxiang.security.app.validate.code.impl;

import com.dabenxiang.security.core.validate.code.ValidateCode;
import com.dabenxiang.security.core.validate.code.ValidateCodeException;
import com.dabenxiang.security.core.validate.code.ValidateCodeRepository;
import com.dabenxiang.security.core.validate.code.ValidateCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * Date:2018/9/21
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Repository
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void save(ServletWebRequest request, ValidateCodeType type, ValidateCode validateCode) {
        redisTemplate.opsForValue().set(builderKey(request,type),validateCode,30, TimeUnit.MINUTES);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        return (ValidateCode) redisTemplate.opsForValue().get(builderKey(request,type));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
        redisTemplate.delete(builderKey(request,type));
    }

    private String builderKey(ServletWebRequest request,ValidateCodeType type){
        String deviceID = request.getHeader("deviceID");
        if(deviceID==null){
            throw new ValidateCodeException("请求头需要带上deviceID");
        }
        return "devideID:"+deviceID+"type:"+type.toString().toLowerCase()+"code:";
    }

}
