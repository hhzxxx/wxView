package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.ProductTemperatureMapper;
import com.qxt.bysj.domain.ProductBrand;
import com.qxt.bysj.domain.ProductTemperature;
import com.qxt.bysj.service.ProductTemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductTemperaturelmpl extends BaseServiceImpl<ProductTemperature> implements ProductTemperatureService {

    @Autowired
    private ProductTemperatureMapper productTemperatureMapper;

    @Override
    public void dealSurvey4Temperature(Integer[] ids){
        if(ids.length>0){
            Map<String,Object> map = new HashMap<>();
            map.put("ids",ids);
            List<ProductTemperature> list = productTemperatureMapper.find(map);
            for(ProductTemperature obj : list){
                if(obj.getHot()==null) obj.setHot(0);
                obj.setHot(obj.getHot()+1);
                productTemperatureMapper.updateByPrimaryKey(obj);
            }
        }
    }
}
