package com.qxt.bysj.service;

import com.qxt.bysj.domain.ProductBrand;

public interface ProductBrandService extends BaseService<ProductBrand>{
    void dealSurvey4Brand(Integer[] ids);
}
