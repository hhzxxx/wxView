package com.qxt.bysj.service;

import com.qxt.bysj.domain.ProductType;

public interface ProductTypeService extends BaseService<ProductType>{
    void dealSurvey4Type(Integer[] ids);
}
