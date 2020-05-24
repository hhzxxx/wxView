package com.qxt.bysj.controller;

import com.qxt.bysj.domain.*;
import com.qxt.bysj.service.*;
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
import java.util.*;

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

    @RequestMapping(value = "/surveyResult", method = RequestMethod.GET)
    public String surveyResult(ModelMap modelMap) {
        List<Product> list = productService.find(new HashMap<>());
        modelMap.put("productNum",list.size());
        List<ProductSurvey> list1 = productSurveyService.find(new HashMap<>());
        modelMap.put("surveyNum",list1.size());
        List<String> openids = new ArrayList<>();

        List<ProductTemperature> tempList = productTemperatureService.find(new HashMap<>());
        List<Map<String,Object>> tempChartList = new ArrayList<>();
        List<Integer> tempIds = new ArrayList<>();

        List<ProductTaste> tasteList = productTasteService.find(new HashMap<>());
        List<Map<String,Object>> tasteChartList = new ArrayList<>();
        List<Integer> tasteIds = new ArrayList<>();
        for(ProductTemperature obj : tempList) {
            Map<String,Object> map = new HashMap<>();
            map.put("value",0);
            map.put("name",obj.getTemperaturename());
            tempChartList.add(map);
            tempIds.add(obj.getId());
        }
        for(ProductTaste obj : tasteList) {
            Map<String,Object> map = new HashMap<>();
            map.put("value",0);
            map.put("name",obj.getTastename());
            tasteChartList.add(map);
            tasteIds.add(obj.getId());
        }
        for(ProductSurvey survey : list1){
            String temp= survey.getTemplist();
            if(temp.length()>2){
                temp = temp.substring(1,temp.length()-1);
                List<String> temps = Arrays.asList(temp.split(","));
                for(String id : temps){
                    id = id.trim();
                    Integer value = (Integer) tempChartList.get(tempIds.indexOf(Integer.valueOf(id))).get("value");
                    value++;
                    tempChartList.get(tempIds.indexOf(Integer.valueOf(id))).replace("value",value);
                }
            }

            String taste= survey.getTastelist();
            if(taste.length()>2){
                taste = taste.substring(1,taste.length()-1);
                List<String> tastes = Arrays.asList(taste.split(","));
                for(String id : tastes){
                    id = id.trim();
                    Integer value = (Integer) tasteChartList.get(tasteIds.indexOf(Integer.valueOf(id))).get("value");
                    value++;
                    tasteChartList.get(tasteIds.indexOf(Integer.valueOf(id))).replace("value",value);
                }
            }

            if (openids.contains(survey.getOpenid())) continue;
            openids.add(survey.getOpenid());
        }
        modelMap.put("tempChartList",tempChartList);
        modelMap.put("tasteChartList",tasteChartList);
        modelMap.put("peopleNum",openids.size());



        if(list1.size()>10) list1 = list1.subList(0,10);
        modelMap.put("surveyList",list1);
        return "page/surveyResult";
    }
}
