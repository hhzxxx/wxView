package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.ManagerMapper;
import com.qxt.bysj.dao.UserMapper;
import com.qxt.bysj.domain.Manager;
import com.qxt.bysj.domain.User;
import com.qxt.bysj.service.ManagerService;
import com.qxt.bysj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl extends BaseServiceImpl<Manager> implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public Manager selectByAccount(String account) {
        return managerMapper.selectByAccount(account);
    }

}
