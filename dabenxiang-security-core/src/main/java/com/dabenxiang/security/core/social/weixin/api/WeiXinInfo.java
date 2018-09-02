package com.dabenxiang.security.core.social.weixin.api;

import lombok.Data;

/**
 * Date:2018/9/2
 * Author:gyc
 * Desc:
 */
@Data
public class WeiXinInfo {

    private String openid;
    private String nickname;
    private String sex;//:1,
    private String province;
    private String city;
    private String country;
    private String headimgurl;//: ;//http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0;//,
    private String privilege;
    private String unionid;//: ;// o6_bmasdasdsad6_2sgVt7hMZOPfL;//

}
