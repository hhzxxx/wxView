package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.BaseMapper;
import com.qxt.bysj.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author huanghz
 * @Date 2020/2/13 11:18
 * @Version 1.0
 */
public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    private BaseMapper<T> BaseDao;

    @Override
    public int deleteById(Integer id) {
        return BaseDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T record) {
        return BaseDao.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return BaseDao.insertSelective(record);
    }

    @Override
    public T selectById(Integer id) {
        return BaseDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateSelective(T record) {
        return BaseDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int update(T record) {
        return BaseDao.updateByPrimaryKey(record);
    }
}
