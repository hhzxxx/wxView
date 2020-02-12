package com.qxt.bysj.dao;

import com.qxt.bysj.domain.Video;

public interface VideoMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(Video record);


    int insertSelective(Video record);


    Video selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(Video record);


    int updateByPrimaryKey(Video record);

    Video selectByAvid(Integer avid);
}