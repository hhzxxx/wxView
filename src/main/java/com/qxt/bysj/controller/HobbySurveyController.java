package com.qxt.bysj.controller;

import com.qxt.bysj.domain.*;
import com.qxt.bysj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/hobbySurveyPage", method = RequestMethod.POST)
@Controller
public class HobbySurveyController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductBrandService productBrandService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductTemperatureService productTemperatureService;
    @Autowired
    private ProductTasteService productTasteService;
    @Autowired
    private ProductSurveyService productSurveyService;

    @RequestMapping(value = "/hobbySurvey", method = RequestMethod.GET)
    public String hobbySurvey() {
        return "page/hobbySurvey";
    }

    @RequestMapping(value = "/findBand", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> findBand(Model model) {
        Result<Object> result = new Result<>();
        Map<String, Object> map = new HashMap<>();
        List<ProductBrand> brand = productBrandService.find(map);
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

    @RequestMapping(value = "/findTaste", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> findTaste(Model model) {
        Result<Object> result = new Result<>();
        Map<String, Object> map = new HashMap<>();
        List<ProductTaste> list = productTasteService.find(map);
        result.setData(list);
        return result;
    }

    @RequestMapping(value = "/findTemp", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> findTemp(Model model) {
        Result<Object> result = new Result<>();
        Map<String, Object> map = new HashMap<>();
        List<ProductTemperature> list = productTemperatureService.find(map);
        result.setData(list);
        return result;
    }

    @RequestMapping(value = "/findProduct", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> findProduct(@RequestBody Product product, HttpServletRequest request, HttpServletResponse response, Model model) {
        Result<Object> result = new Result<>();
        Integer[] brandIds = product.getBrandIds();
        Integer[] typeIds = product.getTypeIds();
        Map<String, Object> map = new HashMap<>();
        map.put("brandIds",brandIds);
        map.put("typeIds",typeIds);
        List<Product> productList = productService.find(map);
        result.setData(productList);
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> save(@RequestBody ProductSurvey productSurvey,Model model) {
        Result<Object> result = new Result<>();
        try {
            productSurveyService.dealSurvey(productSurvey);
            result.setCode("200");
            result.setMessage("操作成功！");
        }catch (Exception e){
            result.setCode("0");
            result.setMessage("操作失败！");
            System.out.println(e.getMessage());
        }finally {
            return result;
        }
    }

}
