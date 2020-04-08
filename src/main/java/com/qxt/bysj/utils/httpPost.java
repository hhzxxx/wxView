package com.qxt.bysj.utils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huanghz
 * @date 2020/3/22 22:42
 */
@Component
public class httpPost {
    private HttpClient httpClient;

    public HttpEntity getEntityByHttpPostMethod(Integer avid) {
        httpClient = HttpClients.custom().build(); //初始化httpclient
        HttpPost httpPost = new HttpPost("https://www.xbeibeix.com/api/bilibili.php"); //使用的请求方法
        //请求头配置
        httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        httpPost.setHeader("Cache-Control", "max-age=0");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36"); //这项内容很重要
        HttpResponse response = null;
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        paramList.add(new BasicNameValuePair("urlav", "https://www.bilibili.com/video/av"+avid));
        paramList.add(new BasicNameValuePair("sxe", "zunzhongbanquan202003250013"));
        paramList.add(new BasicNameValuePair("zengqiang", "true"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(paramList, "utf-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            response = httpClient.execute(httpPost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity httpEntity = response.getEntity();  //获取网页内容流
        return httpEntity;
    }

    public String getPostStr(Integer avid, String code) {
        //获取Html内容
        try {
            return EntityUtils.toString(getEntityByHttpPostMethod(avid), code);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
