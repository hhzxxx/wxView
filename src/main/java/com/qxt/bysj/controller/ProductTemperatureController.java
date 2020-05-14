package com.qxt.bysj.controller;

import com.qxt.bysj.domain.ProductTemperature;
import com.qxt.bysj.service.ProductTemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/ProductTemperature", method = RequestMethod.POST)
public class ProductTemperatureController extends BaseController<ProductTemperature,ProductTemperatureService> {
    @Autowired
    private ProductTemperatureService ProductTemperatureService;
    @Override
    protected ProductTemperatureService service() {
        return ProductTemperatureService;
    }

}
