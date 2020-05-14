package com.qxt.bysj.controller;

import com.qxt.bysj.domain.PageRequest;
import com.qxt.bysj.domain.PageResult;
import com.qxt.bysj.domain.ProductType;
import com.qxt.bysj.domain.Result;
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

@Controller//以json格式输出
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
