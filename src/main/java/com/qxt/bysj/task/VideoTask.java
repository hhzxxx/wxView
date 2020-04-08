package com.qxt.bysj.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qxt.bysj.service.VideoService;
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
public class VideoTask {
    private VideoService videoService;

    @Autowired
    public VideoTask(VideoService service){
        this.videoService = service;
    }

    @Autowired
    private BiliRequest biliRequest;

    @Scheduled(initialDelay = 1000, fixedDelay = 1200*1000 )
    public void biliTask() throws JSONException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println("viedeoTask:"+date);
        //最新排行
        String obj =  biliRequest.getHTMLContentByHttpGetMethod("https://api.bilibili.com/x/web-interface/newlist?rid=76&pn=1&ps=50",null);
        //热门排行
//        String obj =  biliRequest.getHTMLContentByHttpGetMethod(
//                "https://s.search.bilibili.com/cate/search?search_type=video&view_type=hot_rank&cate_id=76&page=1&pagesize=100&time_from=20191204&time_to=20200211",null);
        JSONObject jsonObject = JSON.parseObject(obj);//将爬虫所得的json字符串转为json对象
        JSONObject data = jsonObject.getJSONObject("data");//获取json对象里的data对象
        JSONArray list = data.getJSONArray("archives");//获取data对象里的archives数组
        for(int i=0;i<list.size();i++){
            JSONObject jsonObj = (JSONObject) list.get(i);
            videoService.dealTaskVideo(jsonObj);
        }
    }
}
