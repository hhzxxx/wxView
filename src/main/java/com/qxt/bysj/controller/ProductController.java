package com.qxt.bysj.controller;

import com.qxt.bysj.domain.Product;
import com.qxt.bysj.domain.ProductBrand;
import com.qxt.bysj.domain.ProductType;
import com.qxt.bysj.service.ProductBrandService;
import com.qxt.bysj.service.ProductService;
import com.qxt.bysj.service.ProductTypeService;
import com.qxt.bysj.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/Product", method = RequestMethod.POST)
public class ProductController extends BaseController<Product,ProductService> {
    @Autowired
    private ProductService productService;
    @Override
    protected ProductService service() {
        return productService;
    }
    @Autowired
    private ProductBrandService productBrandService;
    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String productList() {
        return "page/productList";
    }

}
