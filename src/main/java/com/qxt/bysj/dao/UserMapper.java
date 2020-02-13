package com.qxt.bysj.dao;

import com.qxt.bysj.domain.User;

public interface UserMapper extends BaseMapper<User> {

    User selectByOpenid(String openid);

}