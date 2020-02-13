package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.UserMapper;
import com.qxt.bysj.domain.User;
import com.qxt.bysj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserMapper UserDao;

    @Override
    public User selectByOpenid(String openid) {
        return UserDao.selectByOpenid(openid);
    }

}
