package com.qxt.bysj.service;

import com.alibaba.fastjson.JSONObject;
import com.qxt.bysj.domain.Video;

public interface  VideoService {
    int deleteById(Integer id);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectById(Integer id);

    int updateSelective(Video record);

    int update(Video record);

    Video selectByAvid(Integer avid);

    int dealTaskVideo(JSONObject jsonObj);
}
