package com.qxt.bysj.dao;

import com.qxt.bysj.domain.User;

public interface UserMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(User record);


    int insertSelective(User record);


    User selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByOpenid(String openid);

}