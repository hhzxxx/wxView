package com.qxt.bysj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxt.bysj.dao.ProductMapper;
import com.qxt.bysj.domain.Product;
import com.qxt.bysj.domain.dto.ruleDto;
import com.qxt.bysj.service.ProductService;
import com.qxt.bysj.utils.PageRequest;
import com.qxt.bysj.utils.PageResult;
import com.qxt.bysj.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

    @Autowired
    private ProductMapper productDao;

//    @Override
//    public int deleteById(Integer id) {
//        return productDao.deleteByPrimaryKey(id);
//    }
//
//    @Override
//    public int insert(Product record) {
//        return productDao.insert(record);
//    }
//
//    @Override
//    public int update(Product record) {
//        return productDao.updateByPrimaryKey(record);
//    }

//    @Override
//    public PageResult findPage(PageRequest pageRequest) {
//        return PageUtils.getPageResult(pageRequest, getIndexPage(pageRequest));
//    }
    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<Product> getIndexPage(PageRequest pageRequest) {
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
        List<Product> sysMenus = productDao.findPage(map);
        return new PageInfo<Product>(sysMenus);
    }
}
