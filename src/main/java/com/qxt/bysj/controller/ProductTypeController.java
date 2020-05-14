package com.qxt.bysj.controller;

import com.qxt.bysj.domain.ProductType;
import com.qxt.bysj.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/ProductType", method = RequestMethod.POST)
public class ProductTypeController extends BaseController<ProductType,ProductTypeService> {
    @Autowired
    private ProductTypeService productTypeService;
    @Override
    protected ProductTypeService service() {
        return productTypeService;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String typeManage() {
        return "page/typeManage";
    }

}
