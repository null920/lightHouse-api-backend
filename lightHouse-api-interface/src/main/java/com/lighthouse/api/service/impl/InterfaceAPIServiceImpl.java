package com.lighthouse.api.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.lighthouse.api.constant.RelUrl;
import com.lighthouse.api.service.InterfaceAPIService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author null&&
 * @Date 2024/6/9 10:42
 */
@Service
public class InterfaceAPIServiceImpl implements InterfaceAPIService {

    private static final String DYE_COLOR_FLAG = "Ture-Flag";

    private String getURL(String responseBody) {
        if (responseBody.contains("<title>404 Not Found</title>")) {
            throw new RuntimeException("接口失效");
        }
        Pattern pattern = Pattern.compile("<a\\s+href\\s*=\\s*\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(responseBody);
        if (!matcher.find()) {
            return responseBody;
        }
        return matcher.group(1);
    }

    @Override
    public String getFishedCalendar() {
        HttpResponse response = HttpRequest
                .get(RelUrl.FISHED_URL)
                .execute();
        String href = getURL(response.body());
        HttpResponse res = HttpRequest
                .get(href)
                .execute();
        String resultBody = res.body();
        if (resultBody.contains("<title>404 Not Found</title>")) {
            throw new RuntimeException("接口失效");
        }
        JSONObject jsonObject = new JSONObject(resultBody);
        String moyuUrl = jsonObject.getJSONObject("data").getString("moyu_url");
        return "{\"url\":\"" + moyuUrl + "\"}";
    }

    @Override
    public String getWorkerCalendar() {
        HttpResponse response = HttpRequest
                .get(RelUrl.WORKER_URL)
                .execute();
        return "{\"url\":\"" + getURL(response.body()) + "\"}";
    }

    @Override
    public String getSixtySecondsWorld() {
        HttpResponse response = HttpRequest
                .get(RelUrl.WORLD_URL)
                .execute();
        return response.body();
    }

    public void authentication(HttpServletRequest request) {
        String dyeColor = request.getHeader("X-Dye-Color");
        if (!DYE_COLOR_FLAG.equals(dyeColor)) {
            throw new RuntimeException("无权限");
        }
    }
}
