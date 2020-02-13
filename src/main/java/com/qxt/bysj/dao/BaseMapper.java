package com.qxt.bysj.dao;

/**
 * @Author huanghz
 * @Date 2020/2/13 11:21
 * @Version 1.0
 */
public interface BaseMapper<T> {
    int deleteByPrimaryKey(Integer id);


    int insert(T record);


    int insertSelective(T record);


    T selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}
