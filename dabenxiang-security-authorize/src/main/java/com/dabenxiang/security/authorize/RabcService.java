package com.dabenxiang.security.authorize;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Date:2018/9/30
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public interface RabcService {
     boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
