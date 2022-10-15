package com.mhsj.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mhsj.demo.pojo.entity.ResponseResult;
import com.mhsj.demo.service.UserPwdService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static enums.AppHttpCodeEnum.SYSTEM_ERROR;

/**
 * 小程序登录接口
 *
 */
@RestController
@RequestMapping("/mini")
public class miniLoginController {

    @Autowired
    private UserPwdService userPwdService;

    @Value("${mini.appid}")
    private String appid;

    @Value("${mini.secret}")
    private String secret;

    @GetMapping("/minlogin")
    @Transactional
    public ResponseResult login(String code) throws IOException {
        if (StringUtils.isEmpty(code)){
            return ResponseResult.errorResult(SYSTEM_ERROR);
        }
        System.out.println(appid);

        //GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code

        String url = "https://api.weixin.qq.com/sns/jscode2session?" + "appid=" +
                appid +
                "&secret=" +
                secret +
                "&js_code=" +
                code +
                "&grant_type=authorization_code";

        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        //发送数据
        //发送数据
        CloseableHttpResponse response = build.execute(get);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        JSONObject jsonObject = JSON.parseObject(result);
        String openid = jsonObject.getString("openid");
        return userPwdService.login(openid,null);
    }

}
