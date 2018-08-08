package com.dabenxiang.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Date:2018/7/31
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */
public class MockServer {

    public static  void main(String[] args){

        WireMock.configureFor(6060);
        WireMock.removeAllMappings();
//        WireMock.moc
    }

    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
        stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
    }
}
