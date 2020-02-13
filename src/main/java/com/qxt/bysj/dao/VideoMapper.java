package com.qxt.bysj.dao;

import com.qxt.bysj.domain.Video;

public interface VideoMapper extends BaseMapper<Video> {

    Video selectByAvid(Integer avid);
}