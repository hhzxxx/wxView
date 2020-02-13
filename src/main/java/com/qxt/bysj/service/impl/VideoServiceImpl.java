package com.qxt.bysj.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qxt.bysj.dao.VideoMapper;
import com.qxt.bysj.domain.Video;
import com.qxt.bysj.service.TagService;
import com.qxt.bysj.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class VideoServiceImpl extends BaseServiceImpl<Video> implements VideoService {
    @Autowired
    private VideoMapper videoDao;
    @Autowired
    private TagService tagService;

    @Override
    public Video selectByAvid(Integer avid) {
        return videoDao.selectByAvid(avid);
    }

    @Override
    public int dealTaskVideo(JSONObject jsonObj){
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer avid = Integer.valueOf(jsonObj.getString("aid"));
        Video video =  new Video();
        Video video1 = videoDao.selectByAvid(avid);
        int flag = 0;
        if(video1 == null) {
            flag = 1;
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
        if(flag == 1){
            tagService.dealTaskVideoTag(video);
        }
        return 1;
    }
}
