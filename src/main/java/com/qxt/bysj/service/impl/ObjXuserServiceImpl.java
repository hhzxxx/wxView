package com.qxt.bysj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxt.bysj.dao.ObjXuserMapper;
import com.qxt.bysj.domain.*;
import com.qxt.bysj.domain.dto.ruleDto;
import com.qxt.bysj.service.*;
import com.qxt.bysj.utils.PageRequest;
import com.qxt.bysj.utils.PageResult;
import com.qxt.bysj.utils.PageUtils;
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
    private ArticleService articleService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private TagXuserService tagXuserService;
    @Autowired
    private TagService tagService;

    @Override
    public PageResult findOldAndCollectPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getOldAndCollectPage(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<ObjXuser> getOldAndCollectPage(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ruleDto> rules = pageRequest.getRules();
        Map<String, Object> map = new HashMap<>();
        if(rules.size()>0){
            for(int i=0;i<rules.size();i++){
                map.put(rules.get(i).getRuleName(),rules.get(i).getRuleValue());
            }
        }
        List<ObjXuser> sysMenus = objXuserMapper.findOldAndCollectPage(map);
        return new PageInfo<ObjXuser>(sysMenus);
    }


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
            updateVideoAction(objId,action,entity, objType);
        }else if(objType==2){
            updateArticleAction(objId,action,entity, objType);
        }else if(objType==3){
            updateReplyAction(objId,action,entity);
        }
    }

    private void updateTag(List<Tag> tagList,Integer userId) {
        if (tagList.size() > 0) {
            for (Tag tag : tagList) {
                tag.setHot(tag.getHot()+5);
                tagService.update(tag);
                Map<String, Object> query2 = new HashMap<>();
                query2.put("userId", userId);
                query2.put("tagId", tag.getId());

                List<TagXuser> xuserList = tagXuserService.find(query2);
                if (xuserList.size() > 0) {
                    for (TagXuser tagXuser : xuserList) {
                        if (tagXuser.getHot() == null) tagXuser.setHot(0);
                        tagXuser.setHot(tagXuser.getHot() + 1);
                        tagXuserService.update(tagXuser);
                    }
                } else {
                    TagXuser obj = new TagXuser();
                    obj.setTagid(tag.getId());
                    obj.setUserid(userId);
                    obj.setHot(1);
                    tagXuserService.insert(obj);
                }
            }
        }
    }

    private void updateArticleAction(Integer objId,String action,ObjXuser entity,Integer objType){
        Article article = articleService.selectById(objId);
        Map<String, Object> query1 = new HashMap<>();
        query1.put("articleId",objId);
        query1.put("type",objType);
        List<Tag> tagList = tagService.find(query1);

        if(action.equals("good")){
            if (entity.getIsgood()==null ||entity.getIsgood()==0){
                entity.setIsgood(1);
                article.setHot(article.getHot()+5);
                article.setGood(article.getGood()+1);

                updateTag(tagList,entity.getUserid());
            }else {
                entity.setIsgood(0);
                article.setHot(article.getHot()-5);
                if(article.getGood()>0){
                    article.setGood(article.getGood()-1);
                }
            }
        }
        if(action.equals("bad")){
            if (entity.getIsbad()==null || entity.getIsbad()==0){
                entity.setIsbad(1);
                article.setHot(article.getHot()-3);
                article.setBad(article.getBad()+1);
            }else {
                entity.setIsbad(0);
                article.setHot(article.getHot()+3);
                if(article.getBad()>0){
                    article.setBad(article.getBad()-1);
                }
            }
        }
        if(action.equals("collection")){
            if (entity.getIscollection()==null||entity.getIscollection()==0){
                entity.setIscollection(1);
                entity.setCollectiontime(new Date());
                article.setHot(article.getHot()+5);
                article.setCollection(article.getCollection()+1);
                updateTag(tagList,entity.getUserid());
            }else {
                entity.setIscollection(0);
                article.setHot(article.getHot()-5);
                if(article.getCollection()>0){
                    article.setCollection(article.getCollection()-1);
                }
            }
        }
        if(action.equals("reply")){
            article.setHot(article.getHot()+5);
            updateTag(tagList,entity.getUserid());
        }
        articleService.update(article);
        objXuserMapper.updateByPrimaryKey(entity);
    }

    private void updateVideoAction(Integer objId,String action,ObjXuser entity,Integer objType){
        Video video = videoService.selectById(objId);
        Map<String, Object> query1 = new HashMap<>();
        query1.put("videoId",objId);
        query1.put("type",objType);
        List<Tag> tagList = tagService.find(query1);
        if(action.equals("good")){
            if (entity.getIsgood()==null ||entity.getIsgood()==0){
                entity.setIsgood(1);
                video.setHot(video.getHot()+5);
                video.setGood(video.getGood()+1);
                updateTag(tagList,entity.getUserid());
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
                updateTag(tagList,entity.getUserid());
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
            updateTag(tagList,entity.getUserid());
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

                Map<String, Object> query1 = new HashMap<>();
                query1.put("articleId",objId);
                query1.put("article",2);
                List<Tag> tagList = tagService.find(query1);
                updateTag(tagList,entity.getUserid());
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
