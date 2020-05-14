package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.ProductMapper;
import com.qxt.bysj.dao.ProductTasteMapper;
import com.qxt.bysj.domain.Product;
import com.qxt.bysj.domain.ProductTaste;
import com.qxt.bysj.service.ProductService;
import com.qxt.bysj.service.ProductTasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTasteImpl extends BaseServiceImpl<ProductTaste> implements ProductTasteService {

    @Autowired
    private ProductTasteMapper productTasteMapper;

}
