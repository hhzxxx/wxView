package com.qxt.bysj.dao;

import com.qxt.bysj.domain.Manager;
import com.qxt.bysj.domain.User;

public interface ManagerMapper extends BaseMapper<Manager> {

    Manager selectByAccount(String account);

}