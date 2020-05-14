package com.qxt.bysj.service;

import com.qxt.bysj.domain.ProductTemperature;

public interface ProductTemperatureService extends BaseService<ProductTemperature>{
    void dealSurvey4Temperature(Integer[] ids);
}
