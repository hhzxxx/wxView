package com.qxt.bysj.threads;

import com.qxt.bysj.domain.*;
import com.qxt.bysj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author huanghz
 * @Date 2020/3/12 16:50
 * @Version 1.0
 */
@Service
public class BusinessService {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TagXuserService tagXuserService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private ObjXuserService videoXuserService;

    public void doVideoTap(String openId,Integer objId,Integer objType){
        if(openId!=null && openId.length()>0 && objType==1){
            User user = userService.selectByOpenid(openId);
            Video video = videoService.selectById(objId);
            if(video.getHot()==null) video.setHot(0);
            video.setHot(video.getHot()+1);
            videoService.update(video);

            Map<String, Object> query1 = new HashMap<>();
            query1.put("videoId",objId);
            query1.put("video",1);
            List<Tag> tagList = tagService.find(query1);
            if(tagList.size()>0){
                for(Tag tag:tagList){
                    if(tag.getHot()==null) tag.setHot(0);
                    tag.setHot(tag.getHot()+1);
                    tagService.update(tag);

                    Map<String, Object> query2 = new HashMap<>();
                    query2.put("userId",user.getId());
                    query2.put("tagId",tag.getId());

                    List<TagXuser> xuserList = tagXuserService.find(query2);
                    if(xuserList.size()>0){
                        for(TagXuser tagXuser:xuserList){
                            if(tagXuser.getHot()==null) tagXuser.setHot(0);
                            tagXuser.setHot(tagXuser.getHot()+1);
                            tagXuserService.update(tagXuser);
                        }
                    }else {
                        TagXuser obj = new TagXuser();
                        obj.setTagid(tag.getId());
                        obj.setUserid(user.getId());
                        obj.setHot(1);
                        tagXuserService.insert(obj);
                    }
                }
            }
        }else if(openId!=null && openId.length()>0 && objType==2){
            User user = userService.selectByOpenid(openId);
            Article article = articleService.selectById(objId);
            if(article.getHot()==null) article.setHot(0);
            article.setHot(article.getHot()+1);
            articleService.update(article);

            Map<String, Object> query1 = new HashMap<>();
            query1.put("articleId",objId);
            query1.put("article",2);
            List<Tag> tagList = tagService.find(query1);
            if(tagList.size()>0){
                for(Tag tag:tagList){
                    if(tag.getHot()==null) tag.setHot(0);
                    tag.setHot(tag.getHot()+1);
                    tagService.update(tag);

                    Map<String, Object> query2 = new HashMap<>();
                    query2.put("userId",user.getId());
                    query2.put("tagId",tag.getId());

                    List<TagXuser> xuserList = tagXuserService.find(query2);
                    if(xuserList.size()>0){
                        for(TagXuser tagXuser:xuserList){
                            if(tagXuser.getHot()==null) tagXuser.setHot(0);
                            tagXuser.setHot(tagXuser.getHot()+1);
                            tagXuserService.update(tagXuser);
                        }
                    }else {
                        TagXuser obj = new TagXuser();
                        obj.setTagid(tag.getId());
                        obj.setUserid(user.getId());
                        obj.setHot(1);
                        tagXuserService.insert(obj);
                    }
                }
            }
        }
    }
}
