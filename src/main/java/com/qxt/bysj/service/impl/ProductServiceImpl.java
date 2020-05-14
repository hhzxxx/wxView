package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.ProductMapper;
import com.qxt.bysj.domain.Product;
import com.qxt.bysj.domain.ProductTemperature;
import com.qxt.bysj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void dealSurvey4Product(Integer[] ids){
        if(ids.length>0){
            Map<String,Object> map = new HashMap<>();
            map.put("ids",ids);
            List<Product> list = productMapper.find(map);
            for(Product obj : list){
                if(obj.getHot()==null) obj.setHot(0);
                obj.setHot(obj.getHot()+1);
                productMapper.updateByPrimaryKey(obj);
            }
        }
    }
}
