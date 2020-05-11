package com.qxt.bysj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxt.bysj.dao.ProductTypeMapper;
import com.qxt.bysj.domain.PageRequest;
import com.qxt.bysj.domain.ProductType;
import com.qxt.bysj.domain.dto.ruleDto;
import com.qxt.bysj.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductTypeServicelmpl extends BaseServiceImpl<ProductType> implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeDao;

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<ProductType> getIndexPage(PageRequest pageRequest) {
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
        List<ProductType> sysMenus = productTypeDao.findPage(map);
        return new PageInfo<ProductType>(sysMenus);
    }
}
