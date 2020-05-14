package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.ProductTypeMapper;
import com.qxt.bysj.domain.ProductTaste;
import com.qxt.bysj.domain.ProductType;
import com.qxt.bysj.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductTypeServicelmpl extends BaseServiceImpl<ProductType> implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public void dealSurvey4Type(Integer[] ids){
        if(ids.length>0){
            Map<String,Object> map = new HashMap<>();
            map.put("ids",ids);
            List<ProductType> list = productTypeMapper.find(map);
            for(ProductType obj : list){
                if(obj.getHot()==null) obj.setHot(0);
                obj.setHot(obj.getHot()+1);
                productTypeMapper.updateByPrimaryKey(obj);
            }
        }
    }
}
