package com.qxt.bysj.service;

import com.qxt.bysj.domain.User;

public interface  UserService {
    int deleteById(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectById(Integer id);

    int updateSelective(User record);

    int update(User record);

}
