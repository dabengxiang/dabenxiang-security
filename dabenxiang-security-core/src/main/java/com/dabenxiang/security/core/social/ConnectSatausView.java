package com.dabenxiang.security.core.social;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import sun.security.provider.certpath.OCSPResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Date:2018/9/4
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@Component("connect/status")
public class ConnectSatausView extends AbstractView {
    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        Map<String, Boolean> returnData = new HashMap<>();

        Set<String> providerIds = (Set) map.get("providerIds");
        for (String providerId : providerIds) {
            Map<String,List> connectionMap = (Map<String, List>) map.get("connectionMap");
            List list = connectionMap.get(providerId);
            if(list!=null && !list.isEmpty())
                returnData.put(providerId,true);
            else
                returnData.put(providerId,false );

        }

        httpServletResponse.getWriter().write(JSONObject.toJSONString(returnData));

    }


//            this.setNoCache(request);
//        this.processFlash(request, model);
//    Map<String, List<Connection<?>>> connections = this.connectionRepository.findAllConnections();
//        model.addAttribute("providerIds", this.connectionFactoryLocator.registeredProviderIds());
//        model.addAttribute("connectionMap", connections);
//        return this.connectView();
}
