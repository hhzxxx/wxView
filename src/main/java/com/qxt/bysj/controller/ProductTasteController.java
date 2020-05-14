package com.qxt.bysj.controller;

import com.qxt.bysj.domain.*;
import com.qxt.bysj.service.ProductTasteService;
import com.qxt.bysj.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/ProductTaste", method = RequestMethod.POST)
public class ProductTasteController extends BaseController<ProductTaste,ProductTasteService> {
    @Autowired
    private ProductTasteService productTasteService;
    @Override
    protected ProductTasteService service() {
        return productTasteService;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String typeManage() {
        return "page/productTaste";
    }

}
