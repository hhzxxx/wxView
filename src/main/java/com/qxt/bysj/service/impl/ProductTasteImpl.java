package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.ProductMapper;
import com.qxt.bysj.dao.ProductTasteMapper;
import com.qxt.bysj.domain.Product;
import com.qxt.bysj.domain.ProductTaste;
import com.qxt.bysj.service.ProductService;
import com.qxt.bysj.service.ProductTasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductTasteImpl extends BaseServiceImpl<ProductTaste> implements ProductTasteService {

    @Autowired
    private ProductTasteMapper productTasteMapper;

    @Override
    public void dealSurvey4Taste(Integer[] ids){
        if(ids.length>0){
            Map<String,Object> map = new HashMap<>();
            map.put("ids",ids);
            List<ProductTaste> list = productTasteMapper.find(map);
            for(ProductTaste obj : list){
                if(obj.getHot()==null) obj.setHot(0);
                obj.setHot(obj.getHot()+1);
                productTasteMapper.updateByPrimaryKey(obj);
            }
        }
    }

}
