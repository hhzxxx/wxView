package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.ProductTypeMapper;
import com.qxt.bysj.domain.ProductType;
import com.qxt.bysj.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServicelmpl extends BaseServiceImpl<ProductType> implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeDao;

}
