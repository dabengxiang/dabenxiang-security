package com.dabenxiang.security.core.social.qq.api;

import lombok.Data;

/**
 * Date:2018/8/30
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Data
public class QQUserInfo {
    private String ret; //:0,

    private String msg; //:; //; //,

    private String openId;

    private String nickname; //:; //Peter; //,

    private String figureurl; //:; //http://qzapp.qlogo.cn/qzapp/111111/942FEA70050EEAFBD4DCE2C1FC775E56/30; //,

    private String figureurl_1; //:; //http://qzapp.qlogo.cn/qzapp/111111/942FEA70050EEAFBD4DCE2C1FC775E56/50; //,

    private String figureurl_2; //:; //http://qzapp.qlogo.cn/qzapp/111111/942FEA70050EEAFBD4DCE2C1FC775E56/100; //,

    private String figureurl_qq_1; //:; //http://q.qlogo.cn/qqapp/100312990/DE1931D5330620DBD07FB4A5422917B6/40; //,

    private String figureurl_qq_2; //:; //http://q.qlogo.cn/qqapp/100312990/DE1931D5330620DBD07FB4A5422917B6/100; //,

    private String gender; //:; //男; //,

    private String is_yellow_vip; //:; //1; //,

    private String vip; //:; //1; //,

    private String yellow_vip_level; //:; //7; //,

    private String level; //:; //7; //,

    private String is_yellow_year_vip; //:; //1; //

}
