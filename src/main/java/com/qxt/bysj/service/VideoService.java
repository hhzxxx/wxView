package com.qxt.bysj.service;

import com.alibaba.fastjson.JSONObject;
import com.qxt.bysj.domain.Video;

public interface  VideoService extends BaseService<Video> {

    Video selectByAvid(Integer avid);

    int dealTaskVideo(JSONObject jsonObj);
}
