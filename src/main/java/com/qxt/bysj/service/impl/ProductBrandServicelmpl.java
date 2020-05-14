package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.ProductBrandMapper;
import com.qxt.bysj.domain.ProductBrand;
import com.qxt.bysj.service.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductBrandServicelmpl extends BaseServiceImpl<ProductBrand> implements ProductBrandService {

    @Autowired
    private ProductBrandMapper productBrandDao;

}
