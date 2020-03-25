package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.VideoXuserMapper;
import com.qxt.bysj.domain.User;
import com.qxt.bysj.domain.Video;
import com.qxt.bysj.domain.VideoXuser;
import com.qxt.bysj.service.UserService;
import com.qxt.bysj.service.VideoService;
import com.qxt.bysj.service.VideoXuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoXuserServiceImpl extends BaseServiceImpl<VideoXuser> implements VideoXuserService {
    @Autowired
    private VideoXuserMapper videoXuserMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private VideoService videoService;

    @Override
    public void doUserAction(String openId, Integer videoId, String action) {
        User user = userService.selectByOpenid(openId);
        Map<String, Object> videoXuserQuery = new HashMap<>();
        videoXuserQuery.put("videoId",videoId);
        videoXuserQuery.put("userId",user.getId());
        List<VideoXuser> videoXuserList = videoXuserMapper.find(videoXuserQuery);
        Video video = videoService.selectById(videoId);
        if(videoXuserList.size()<1){
            VideoXuser entity = new VideoXuser();
            entity.setVideoid(videoId);
            entity.setUserid(user.getId());
            entity.setStatus(0);
            videoXuserMapper.insert(entity);
        }else {
            VideoXuser entity = videoXuserList.get(0);
            if(action.equals("good")){
                if (entity.getIsgood()==null ||entity.getIsgood()==0){
                    entity.setIsgood(1);
                    video.setHot(video.getHot()+5);
                    video.setGood(video.getGood()+1);
                }else {
                    entity.setIsgood(0);
                    video.setHot(video.getHot()-5);
                    video.setGood(video.getGood()-1);
                }
            }
            if(action.equals("bad")){
                if (entity.getIsbad()==null || entity.getIsbad()==0){
                    entity.setIsbad(1);
                    video.setHot(video.getHot()-3);
                    video.setBad(video.getBad()+1);
                }else {
                    entity.setIsbad(0);
                    video.setHot(video.getHot()+3);
                    video.setBad(video.getBad()-1);
                }
            }
            if(action.equals("collection")){
                if (entity.getIscollection()==null||entity.getIscollection()==0){
                    entity.setIscollection(1);
                    entity.setCollectiontime(new Date());
                    video.setHot(video.getHot()+5);
                    video.setCollection(video.getCollection()+1);
                }else {
                    entity.setIscollection(0);
                    video.setHot(video.getHot()-5);
                    video.setCollection(video.getCollection()-1);
                }
            }
            videoService.update(video);
            videoXuserMapper.updateByPrimaryKey(entity);
        }
    }
}
