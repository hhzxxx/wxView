package com.qxt.bysj.controller;

import com.qxt.bysj.domain.ProductBrand;
import com.qxt.bysj.service.ProductBrandService;
import com.qxt.bysj.utils.PageRequest;
import com.qxt.bysj.utils.PageResult;
import com.qxt.bysj.utils.Result;
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
public class brandProductController {
    @Autowired
    private ProductBrandService productBrandService;

    @RequestMapping(value = "/brandManage", method = RequestMethod.GET)
    public String brandManage() {
        return "page/Example/brandManage";
    }

    @RequestMapping(value = "/saveBrandFrom", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> saveBrandFrom(@RequestBody ProductBrand productBrand, HttpServletRequest request, HttpServletResponse response, Model model) {
        Result<Object> result = new Result<>();
        String brandName = productBrand.getBrandname();
        String brandPic = productBrand.getBrandpic();
        String Introduction = productBrand.getIntroduction();
        ProductBrand newProductBrand = new ProductBrand();
        newProductBrand.setBrandname(brandName);
        newProductBrand.setBrandpic(brandPic);
        newProductBrand.setIntroduction(Introduction);
        productBrandService.insert(newProductBrand);
        result.setCode("200");
        result.setMessage("添加成功！");
        return result;
    }

    @RequestMapping(value = "/findBrandDetail", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> findBrandDetail(@RequestBody PageRequest pageQuery, Model model) {
        Result<Object> result = new Result<>();
        PageResult page = productBrandService.findPage(pageQuery);
        result.setData(page);
        return result;
    }

    @RequestMapping(value = "/findByBrandId", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object>  findByBrandId(@RequestBody ProductBrand productBrand, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        Result<Object> result = new Result<>();
        Integer id = productBrand.getId();
        ProductBrand productBrandList = productBrandService.selectById(id);
        result.setData(productBrandList);
        return result;
    }
//
//    @RequestMapping(value = "/delDetail", method = RequestMethod.POST)
//    @ResponseBody
//    public Result<Object> delDetail(@RequestBody ProductBrand productBrand, HttpServletRequest request, HttpServletResponse response) {
//        Result<Object> result = new Result<>();
//        Integer id = product.getId();
//        productService.deleteById(id);
//        result.setCode("200");
//        result.setMessage("删除成功！");
//        return result;
//    }
//
//    @RequestMapping(value = "/updateDetail", method = RequestMethod.POST)
//    @ResponseBody
//    public Result<Object> updateDetail(@RequestBody ProductBrand productBrand, HttpServletRequest request, HttpServletResponse response) {
//        Result<Object> result = new Result<>();
//        productService.update(product);
//        result.setCode("200");
//        result.setMessage("修改成功！");
//        return result;
//    }
}
