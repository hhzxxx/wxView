package com.qxt.bysj.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxt.bysj.dao.VideoMapper;
import com.qxt.bysj.domain.Video;
import com.qxt.bysj.domain.dto.ruleDto;
import com.qxt.bysj.service.OwnerService;
import com.qxt.bysj.service.TagService;
import com.qxt.bysj.service.VideoService;
import com.qxt.bysj.utils.EmojiFilter;
import com.qxt.bysj.domain.PageRequest;
import com.qxt.bysj.domain.PageResult;
import com.qxt.bysj.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VideoServiceImpl extends BaseServiceImpl<Video> implements VideoService {
    @Autowired
    private VideoMapper videoDao;
    @Autowired
    private TagService tagService;
    @Autowired
    private OwnerService ownerService;

    @Override
    public Video selectByAvid(Integer avid) {
        return videoDao.selectByAvid(avid);
    }

    @Override
    public int dealTaskVideo(JSONObject jsonObj,int num){
        if(EmojiFilter.containsEmoji(jsonObj.getString("dynamic"))) return 0;
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
            video.setCid(Integer.valueOf(jsonObj.getString("cid")));
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
        ownerService.dealOwner(video.getOwnerid(),num);
        if(flag == 1){
            tagService.dealTaskVideoTag(video);
        }
        return 1;
    }

    @Override
    public PageResult findIndexPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getIndexPage(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<Video> getIndexPage(PageRequest pageRequest) {
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
        List<Video> sysMenus = videoDao.findIndexPage(map);
        return new PageInfo<Video>(sysMenus);
    }
}
