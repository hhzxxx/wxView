package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.ObjXuserMapper;
import com.qxt.bysj.domain.ObjXuser;
import com.qxt.bysj.domain.Reply;
import com.qxt.bysj.domain.User;
import com.qxt.bysj.domain.Video;
import com.qxt.bysj.service.ObjXuserService;
import com.qxt.bysj.service.ReplyService;
import com.qxt.bysj.service.UserService;
import com.qxt.bysj.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ObjXuserServiceImpl extends BaseServiceImpl<ObjXuser> implements ObjXuserService {
    @Autowired
    private ObjXuserMapper objXuserMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private ReplyService replyService;

    @Override
    public void doUserAction(String openId, Integer objId, String action,Integer objType) {
        User user = userService.selectByOpenid(openId);
        Map<String, Object> objXuserQuery = new HashMap<>();
        objXuserQuery.put("objId",objId);
        objXuserQuery.put("objType",objType);
        objXuserQuery.put("userId",user.getId());
        List<ObjXuser> objXuserList = objXuserMapper.find(objXuserQuery);
        ObjXuser entity = new ObjXuser();
        if(objXuserList.size()<1){
            entity.setObjid(objId);
            entity.setObjtype(objType);
            entity.setUserid(user.getId());
            entity.setStatus(0);
            objXuserMapper.insert(entity);
            entity = objXuserMapper.find(objXuserQuery).get(0);
        }else {
            entity = objXuserList.get(0);
        }
        if(objType==1){
            updateVideoAction(objId,action,entity);
        }else if(objType==2){

        }else if(objType==3){
            updateReplyAction(objId,action,entity);
        }
    }

    private void updateVideoAction(Integer objId,String action,ObjXuser entity){
        Video video = videoService.selectById(objId);
        if(action.equals("good")){
            if (entity.getIsgood()==null ||entity.getIsgood()==0){
                entity.setIsgood(1);
                video.setHot(video.getHot()+5);
                video.setGood(video.getGood()+1);
            }else {
                entity.setIsgood(0);
                video.setHot(video.getHot()-5);
                if(video.getGood()>0){
                    video.setGood(video.getGood()-1);
                }
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
                if(video.getBad()>0){
                    video.setBad(video.getBad()-1);
                }
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
                if(video.getCollection()>0){
                    video.setCollection(video.getCollection()-1);
                }
            }
        }
        if(action.equals("reply")){
                video.setHot(video.getHot()+5);
        }
        videoService.update(video);
        objXuserMapper.updateByPrimaryKey(entity);
    }

    private void updateReplyAction(Integer objId,String action,ObjXuser entity) {
        Reply reply = replyService.selectById(objId);
        if(action.equals("good")){
            if (entity.getIsgood()==null ||entity.getIsgood()==0){
                entity.setIsgood(1);
                reply.setGood(reply.getGood()+1);
            }else {
                entity.setIsgood(0);
                if(reply.getGood()>0){
                    reply.setGood(reply.getGood()-1);
                }
            }
        }
        if(action.equals("bad")){
            if (entity.getIsbad()==null || entity.getIsbad()==0){
                entity.setIsbad(1);
                reply.setBad(reply.getBad()+1);
            }else {
                entity.setIsbad(0);
                reply.setBad(reply.getBad()-1);
                if(reply.getBad()>0){
                    reply.setBad(reply.getBad()-1);
                }
            }
        }
        replyService.update(reply);
        objXuserMapper.updateByPrimaryKey(entity);
    }
}
