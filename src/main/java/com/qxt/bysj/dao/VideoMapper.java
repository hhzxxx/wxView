package com.qxt.bysj.dao;

import com.qxt.bysj.domain.Video;

import java.util.List;
import java.util.Map;

public interface VideoMapper extends BaseMapper<Video> {

    Video selectByAvid(Integer avid);

    /**
     * 首页 通过个人标签获取视频内容
     * @param map
     * @return
     */
    List<Video> findIndexPage(Map<String, Object> map);

    List<Video> findPageOrder(Map<String, Object> map);

}