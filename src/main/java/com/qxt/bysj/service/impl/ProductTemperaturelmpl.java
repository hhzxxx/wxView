package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.ProductTemperatureMapper;
import com.qxt.bysj.domain.ProductTemperature;
import com.qxt.bysj.service.ProductTemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTemperaturelmpl extends BaseServiceImpl<ProductTemperature> implements ProductTemperatureService {

    @Autowired
    private ProductTemperatureMapper productTemperatureMapper;

}
