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
public class typeProductController {
    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping(value = "/typeManage", method = RequestMethod.GET)
    public String typeManage() {
        return "page/typeManage";
    }

    @RequestMapping(value = "/saveTypeFrom", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> saveTypeFrom(@RequestBody ProductType productType, HttpServletRequest request, HttpServletResponse response, Model model) {
        Result<Object> result = new Result<>();
        String typename = productType.getTypename();
        ProductType newProductType = new ProductType();
        newProductType.setTypename(typename);
        productTypeService.insert(newProductType);
        result.setCode("200");
        result.setMessage("添加成功！");
        return result;
    }

    @RequestMapping(value = "/findTypeDetail", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> findTypeDetail(@RequestBody PageRequest pageQuery, Model model) {
        Result<Object> result = new Result<>();
        PageResult page = productTypeService.findPage(pageQuery);
        result.setData(page);
        return result;
    }

    @RequestMapping(value = "/findByTypeId", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> findByTypeId(@RequestBody ProductType productType, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        Result<Object> result = new Result<>();
        Integer id = productType.getId();
        ProductType productBrandList = productTypeService.selectById(id);
        result.setData(productBrandList);
        return result;
    }

    @RequestMapping(value = "/delTypeDetail", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> delTypeDetail(@RequestBody ProductType productType, HttpServletRequest request, HttpServletResponse response) {
        Result<Object> result = new Result<>();
        Integer id = productType.getId();
        productTypeService.deleteById(id);
        result.setCode("200");
        result.setMessage("删除成功！");
        return result;
    }

    @RequestMapping(value = "/updateTypeDetail", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> updateTypeDetail(@RequestBody ProductType productType, HttpServletRequest request, HttpServletResponse response) {
        Result<Object> result = new Result<>();
        productTypeService.update(productType);
        result.setCode("200");
        result.setMessage("修改成功！");
        return result;
    }
}
