package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.ProductMapper;
import com.qxt.bysj.dao.ProductSurveyMapper;
import com.qxt.bysj.domain.Product;
import com.qxt.bysj.domain.ProductSurvey;
import com.qxt.bysj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProductSurveyServiceImpl extends BaseServiceImpl<ProductSurvey> implements ProductSurveyService {

    @Autowired
    private ProductSurveyMapper productSurveyMapper;
    @Autowired
    private ProductBrandService productBrandService;
    @Autowired
    private ProductTasteService productTasteService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductTemperatureService productTemperatureService;
    @Autowired
    private ProductService productService;

    @Override
    public void dealSurvey(ProductSurvey entity){
        Integer[] brandIds = entity.getBrandlist1();
        Integer[] typeIds = entity.getTypelist1();
        Integer[] tasteIds = entity.getTastelist1();
        Integer[] tempIds = entity.getTemplist1();
        Integer[] productIds = entity.getProductlist1();

        entity.setBrandlist(Arrays.toString(brandIds));
        entity.setTypelist(Arrays.toString(typeIds));
        entity.setTastelist(Arrays.toString(tasteIds));
        entity.setTemplist(Arrays.toString(tempIds));
        entity.setProductlist(Arrays.toString(productIds));

        productSurveyMapper.insert(entity);
        productBrandService.dealSurvey4Brand(brandIds);
        productTasteService.dealSurvey4Taste(tasteIds);
        productTypeService.dealSurvey4Type(typeIds);
        productTemperatureService.dealSurvey4Temperature(tempIds);
        productService.dealSurvey4Product(productIds);
    }

}
