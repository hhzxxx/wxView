package com.qxt.bysj.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qxt.bysj.service.ArticleService;
import com.qxt.bysj.utils.BiliRequest;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
@Async
public class ArticleTask {
    private ArticleService articleService;

    @Autowired
    public ArticleTask(ArticleService service){
        this.articleService = service;
    }

    @Autowired
    private BiliRequest biliRequest;

//    @Scheduled(initialDelay = 1000, fixedDelay = 12000*1000 )
    public void biliTask() throws JSONException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println("ArticleTask:"+date);
        //最新排行
        String obj =  biliRequest.getHTMLContentByHttpGetMethod("https://api.bilibili.com/x/article/recommends?cid=13&pn=1&ps=100&jsonp=jsonp&aids=&sort=1",null);
        //热门排行
//        String obj =  biliRequest.getHTMLContentByHttpGetMethod(
//                "https://s.search.bilibili.com/cate/search?search_type=video&view_type=hot_rank&cate_id=76&page=1&pagesize=100&time_from=20191204&time_to=20200211",null);
        JSONObject jsonObject = JSON.parseObject(obj);
        JSONArray list = jsonObject.getJSONArray("data");
        for(int i=0;i<list.size();i++){
            JSONObject jsonObj = (JSONObject) list.get(i);
            articleService.dealTaskArticle(jsonObj,list.size()-i);
        }
    }
}
