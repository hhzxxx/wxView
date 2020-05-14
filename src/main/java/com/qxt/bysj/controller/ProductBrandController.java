package com.qxt.bysj.controller;

import com.qxt.bysj.domain.ProductBrand;
import com.qxt.bysj.service.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/ProductBrand", method = RequestMethod.POST)
public class ProductBrandController extends BaseController<ProductBrand,ProductBrandService> {
    @Autowired
    private ProductBrandService productBrandService;
    @Override
    protected ProductBrandService service() {
        return productBrandService;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String brandManage() {
        return "page/brandManage";
    }

}
