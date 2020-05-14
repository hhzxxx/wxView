package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.ProductMapper;
import com.qxt.bysj.domain.Product;
import com.qxt.bysj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

    @Autowired
    private ProductMapper productDao;

}
