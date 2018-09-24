package com.dabenxiang.security.app.social;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.social.connect.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Date:2018/9/23
 * Author:gyc
 * Desc:
 */

@Component
public class AppSignUpUtils {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;


    public void saveConnectionData(ServletWebRequest request, ConnectionData connectionData){
        redisTemplate.opsForValue().set(getKey(request),connectionData);
    }


    public ConnectionData getConnctionData(ServletWebRequest request){
        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(getKey(request));
        return connectionData;
    }

    public void signUp(ServletWebRequest request,String userId){
        ConnectionData connctionData = getConnctionData(request);
        String providerId = connctionData.getProviderId();
        Connection<?> connection = connectionFactoryLocator.getConnectionFactory(providerId).createConnection(connctionData);
        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);

    }


    private String getKey(ServletWebRequest request){
        String deviceID = request.getHeader("deviceID");
        if(StringUtils.isBlank(deviceID)){
            throw  new InternalAuthenticationServiceException("请求头中没有deviceId");
        }
        return "dabenxiang:security.social.connect:"+deviceID;

    }


}
