package com.qxt.bysj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxt.bysj.dao.ProductBrandMapper;
import com.qxt.bysj.domain.ProductBrand;
import com.qxt.bysj.domain.dto.ruleDto;
import com.qxt.bysj.service.ProductBrandService;
import com.qxt.bysj.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductBrandServicelmpl extends BaseServiceImpl<ProductBrand> implements ProductBrandService {

    @Autowired
    private ProductBrandMapper productBrandDao;

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<ProductBrand> getIndexPage(PageRequest pageRequest) {
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
        List<ProductBrand> sysMenus = productBrandDao.findPage(map);
        return new PageInfo<ProductBrand>(sysMenus);
    }
}
