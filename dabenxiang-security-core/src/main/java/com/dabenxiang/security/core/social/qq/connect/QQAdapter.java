package com.dabenxiang.security.core.social.qq.connect;

import com.dabenxiang.security.core.social.qq.api.QQ;
import com.dabenxiang.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * Date:2018/8/31
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class QQAdapter implements ApiAdapter<QQ> {

    /**
     * 测试qq是否连通
     * @param qq
     * @return
     */
    @Override
    public boolean test(QQ qq) {
        return true;
    }

    /**
     * 设置connectionValues用这个用来显示用户的信息
     * @param qq
     * @param connectionValues
     */
    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
        QQUserInfo userInfo = qq.getUserInfo();
        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setImageUrl(userInfo.getFigureurl_qq_1());
        connectionValues.setProviderUserId(userInfo.getOpenId());
        connectionValues.setProfileUrl(null);
    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
