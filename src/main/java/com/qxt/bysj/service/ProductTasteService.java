package com.qxt.bysj.service;

import com.qxt.bysj.domain.ProductTaste;

public interface ProductTasteService extends BaseService<ProductTaste>{
    void dealSurvey4Taste(Integer[] ids);
}
