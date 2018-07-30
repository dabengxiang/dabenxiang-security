package com.dabenxiang.web.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Date:2018/7/30
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
@Component
public class DeferedResultSet  {

    private Map<String,DeferredResult> results = new HashMap<>();

    public Map<String, DeferredResult> getResults() {
        return results;
    }

    public void setResults(Map<String, DeferredResult> results) {
        this.results = results;
    }
}
