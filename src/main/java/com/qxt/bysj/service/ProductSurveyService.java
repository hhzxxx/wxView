package com.qxt.bysj.service;

import com.qxt.bysj.domain.Product;
import com.qxt.bysj.domain.ProductSurvey;

public interface ProductSurveyService extends BaseService<ProductSurvey>{

    void dealSurvey(ProductSurvey productSurvey);
}
