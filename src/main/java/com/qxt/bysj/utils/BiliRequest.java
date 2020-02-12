package com.qxt.bysj.utils;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BiliRequest {
    private HttpClient httpClient;

    public HttpEntity getEntityByHttpGetMethod(String url) {
        httpClient = HttpClients.custom().build(); //初始化httpclient
        HttpGet httpget = new HttpGet(url); //使用的请求方法
        //请求头配置
        httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpget.setHeader("Accept-Encoding", "gzip, deflate");
        httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        httpget.setHeader("Cache-Control", "max-age=0");
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36"); //这项内容很重要
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity httpEntity = response.getEntity();  //获取网页内容流
        return httpEntity;
    }

    public String getHTMLContentByHttpGetMethod(String url, String code) {
        //获取Html内容
        try {
            return EntityUtils.toString(getEntityByHttpGetMethod(url), code);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //请求页面html文件
    public static String getRawHtml(String keyword, int pagenumber, int firstprice, int endprive) throws URISyntaxException, ClientProtocolException, IOException {
        int page = pagenumber * 2 - 1;
        String url = "https://api.bilibili.com/x/web-interface/newlist";
        List<NameValuePair> nameAndValueList = new ArrayList<NameValuePair>();
        nameAndValueList.add(new BasicNameValuePair("rid", keyword));    //类型
        nameAndValueList.add(new BasicNameValuePair("pn", "1"));   //第几页
        nameAndValueList.add(new BasicNameValuePair("ps", "50"));  //数量
        URI uri = new URIBuilder(url).addParameters(nameAndValueList).build();
        HttpClientContext httpClientContext = HttpClientContext.create();
        List<Header> headerList = Lists.newArrayList(); //请求头添加
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9," +
                "image/webp,image/apng,*/*;q=0.8"));
        headerList.add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate"));
        headerList.add(new BasicHeader(HttpHeaders.CACHE_CONTROL, "max-age=0"));
        headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4,ja;q=0.2," +
                "de;q=0.2"));
        headerList.add(new BasicHeader(HttpHeaders.HOST, "api.bilibili.com"));
        headerList.add(new BasicHeader(HttpHeaders.REFERER, "https://search.jd.com/search"));
        //httpClient初始化
        HttpClient httpClient = HttpClients.custom().setDefaultHeaders(headerList).build();
        //获取响应内容
        HttpUriRequest httpUriRequest = RequestBuilder.get().setUri(uri).build();
        httpClient.execute(httpUriRequest, httpClientContext);
        HttpResponse httpResponse = httpClient.execute(httpUriRequest, httpClientContext);
        //获取返回结果中的实体
        HttpEntity entity = httpResponse.getEntity();
        String html = "<html>" + EntityUtils.toString(entity) + "</html>";
        return html;
    }
}