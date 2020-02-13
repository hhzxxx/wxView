package com.qxt.bysj.service;

/**
 * @Author huanghz
 * @Version 1.0
 */
public interface BaseService<T> {
    int deleteById(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectById(Integer id);

    int updateSelective(T record);

    int update(T record);
}
