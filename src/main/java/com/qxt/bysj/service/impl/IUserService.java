package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.UserMapper;
import com.qxt.bysj.domain.User;
import com.qxt.bysj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUserService implements UserService {
    @Autowired
    private UserMapper UserDao;

    @Override
    public int deleteById(Integer id) {
        return UserDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return UserDao.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return UserDao.insertSelective(record);
    }

    @Override
    public User selectById(Integer id) {
        return UserDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateSelective(User record) {
        return UserDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int update(User record) {
        return UserDao.updateByPrimaryKey(record);
    }

    @Override
    public User selectByOpenid(String openid) {
        return UserDao.selectByOpenid(openid);
    }

}
