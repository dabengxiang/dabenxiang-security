package com.dabenxiang.security.core.social;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import org.w3c.dom.views.DocumentView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Date:2018/9/6
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

public class ConnectView extends AbstractView {


    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        PrintWriter writer = httpServletResponse.getWriter();

        if(map.get("connections")==null){
            writer.print("解绑成功！！");
        }
        else
            writer.print("绑定成功！！");

    }



}
