package com.dabenxiang.security.core.properties;

import lombok.Data;

/**
 * Date:2018/8/31
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Data
public class SocialProperties  {

    private QQProperties qq = new QQProperties();

    private WeiXinProperties weixin = new WeiXinProperties();
}
