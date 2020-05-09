package com.qxt.bysj.controller;

import com.qxt.bysj.domain.ProductBrand;
import com.qxt.bysj.domain.ProductType;
import com.qxt.bysj.service.ProductBrandService;
import com.qxt.bysj.service.ProductTypeService;
import com.qxt.bysj.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/hobbySurveyPage", method = RequestMethod.GET)
public class hobbySurveyController {
    @Autowired
    private ProductBrandService productBrandService;
    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping(value = "/hobbySurvey", method = RequestMethod.GET)
    public String hobbySurvey() {
        return "page/hobbySurvey";
    }

    @RequestMapping(value = "/findBand", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> findBand(Model model) {
        Result<Object> result = new Result<>();
//        PageResult page = productBrandService.find(new HashMap<String, Object>());
        Map<String, Object> map = new HashMap<>();
        List<ProductBrand> brand = productBrandService.find(map);
//        model.addAttribute("page", page);
        result.setData(brand);
        return result;
    }

    @RequestMapping(value = "/findType", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> findType(Model model) {
        Result<Object> result = new Result<>();
        Map<String, Object> map = new HashMap<>();
        List<ProductType> type = productTypeService.find(map);
        result.setData(type);
        return result;
    }
}
