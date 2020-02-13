package com.qxt.bysj.service;

import com.qxt.bysj.domain.User;

public interface  UserService extends BaseService<User> {

    User selectByOpenid(String openid);
}
