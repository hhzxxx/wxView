package com.qxt.bysj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxt.bysj.dao.BaseMapper;
import com.qxt.bysj.domain.dto.ruleDto;
import com.qxt.bysj.service.BaseService;
import com.qxt.bysj.utils.PageRequest;
import com.qxt.bysj.utils.PageResult;
import com.qxt.bysj.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author qxt
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

    @Override
    public List<T> find(Map<String, Object> map) {
        return BaseDao.find(map);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<T> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ruleDto> rules = pageRequest.getRules();
        Map<String, Object> map = new HashMap<>();
        if(rules.size()>0){
            for(int i=0;i<rules.size();i++){
                map.put(rules.get(i).getRuleName(),rules.get(i).getRuleValue());
            }
        }
        List<T> sysMenus = BaseDao.findPage(map);
        return new PageInfo<T>(sysMenus);
    }
}
