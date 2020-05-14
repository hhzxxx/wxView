package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.ProductBrandMapper;
import com.qxt.bysj.domain.ProductBrand;
import com.qxt.bysj.domain.ProductSurvey;
import com.qxt.bysj.service.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductBrandServicelmpl extends BaseServiceImpl<ProductBrand> implements ProductBrandService {

    @Autowired
    private ProductBrandMapper productBrandMapper;

    @Override
    public void dealSurvey4Brand(Integer[] ids){
        if(ids.length>0){
            Map<String,Object> map = new HashMap<>();
            map.put("ids",ids);
            List<ProductBrand> list = productBrandMapper.find(map);
            for(ProductBrand obj : list){
                if(obj.getHot()==null) obj.setHot(0);
                obj.setHot(obj.getHot()+1);
                productBrandMapper.updateByPrimaryKey(obj);
            }
        }
    }
}
