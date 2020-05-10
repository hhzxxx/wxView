package com.qxt.bysj.service;

import com.qxt.bysj.domain.PageRequest;
import com.qxt.bysj.domain.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @Author qxt
 * @Version 1.0
 */
public interface BaseService<T> {
    int deleteById(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectById(Integer id);

    int updateSelective(T record);

    int update(T record);

    /**
     * 查找所有用户
     * @return
     */
    List<T> find(Map<String, Object> map);

    /**
     * 分页查询接口
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * @param pageRequest 自定义，统一分页查询请求
     * @return PageResult 自定义，统一分页查询结果
     */
    PageResult findPage(PageRequest pageRequest);
}
