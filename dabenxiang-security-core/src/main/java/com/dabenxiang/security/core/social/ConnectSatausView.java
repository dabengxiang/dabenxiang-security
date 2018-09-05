package com.dabenxiang.security.core.social;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Date:2018/9/4
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@Component("connect/status")
public class ConnectSatausView extends AbstractView {
    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        System.out.println("1111111111111");
        return ;
    }


//            this.setNoCache(request);
//        this.processFlash(request, model);
//    Map<String, List<Connection<?>>> connections = this.connectionRepository.findAllConnections();
//        model.addAttribute("providerIds", this.connectionFactoryLocator.registeredProviderIds());
//        model.addAttribute("connectionMap", connections);
//        return this.connectView();
}
