package com.qxt.bysj.service;

import com.qxt.bysj.domain.Product;

public interface ProductService extends BaseService<Product>{
    void dealSurvey4Product(Integer[] ids);
}
