package com.dabenxiang.security.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Date:2018/9/30
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class RabcServiceImpl implements RabcService {



    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();


        return false;
    }
}
