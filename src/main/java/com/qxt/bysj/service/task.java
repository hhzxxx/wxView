package com.qxt.bysj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qxt.bysj.dao.VideoMapper;
import com.qxt.bysj.domain.Video;
import com.qxt.bysj.utils.BiliRequest;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
@Async
public class task {

    @Autowired
    private VideoMapper videoDao;

    @Autowired
    private BiliRequest biliRequest;

    @Scheduled(initialDelay = 1000, fixedDelay = 24*3600*1000 )
    public void biliTask() throws JSONException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //最新排行
        String obj =  biliRequest.getHTMLContentByHttpGetMethod("https://api.bilibili.com/x/web-interface/newlist?rid=76&pn=1&ps=50",null);
        //热门排行
//        String obj =  biliRequest.getHTMLContentByHttpGetMethod(
//                "https://s.search.bilibili.com/cate/search?search_type=video&view_type=hot_rank&cate_id=76&page=1&pagesize=100&time_from=20191204&time_to=20200211",null);
        JSONObject jsonObject = JSON.parseObject(obj);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray list = data.getJSONArray("archives");
        for(int i=0;i<list.size();i++){
            JSONObject jsonObj = (JSONObject) list.get(i);
            Integer avid = Integer.valueOf(jsonObj.getString("aid"));
            Video video =  new Video();
            Video video1 = videoDao.selectByAvid(avid);
            if(video1 == null) {
                video.setUpdatetime(date);
                video.setStatus(0);
                video.setCreatetime(date);
                video.setUuid(UUID.randomUUID().toString());
                video.setAvid(avid);
                videoDao.insert(video);
            }
            video = videoDao.selectByAvid(avid);
            video.setUpdatetime(date);
            video.setType(jsonObj.get("tname").toString());
            video.setOwner(jsonObj.getJSONObject("owner").get("name").toString());
            video.setOwnerid((Integer) jsonObj.getJSONObject("owner").get("mid"));
            video.setPic(jsonObj.getString("pic"));
            video.setTitle(jsonObj.getString("title"));
            video.setRemark(jsonObj.getString("dynamic"));
            videoDao.updateByPrimaryKeySelective(video);
        }
    }
}
