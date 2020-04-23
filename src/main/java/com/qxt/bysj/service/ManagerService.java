package com.qxt.bysj.service;

import com.qxt.bysj.domain.Manager;

public interface ManagerService extends BaseService<Manager> {

    Manager selectByAccount(String account);

}
