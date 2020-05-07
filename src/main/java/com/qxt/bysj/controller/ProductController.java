package com.qxt.bysj.controller;

import com.qxt.bysj.domain.Product;
import com.qxt.bysj.domain.ProductBrand;
import com.qxt.bysj.domain.ProductType;
import com.qxt.bysj.service.ProductBrandService;
import com.qxt.bysj.service.ProductService;
import com.qxt.bysj.service.ProductTypeService;
import com.qxt.bysj.utils.PageRequest;
import com.qxt.bysj.utils.PageResult;
import com.qxt.bysj.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller//以json格式输出
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductBrandService productBrandService;
    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping(value = "/basicTable", method = RequestMethod.GET)
    public String basicTable() {
        return "page/Example/basic-table";
    }

    @RequestMapping(value = "/saveFrom", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> saveFrom(@RequestBody Product product, HttpServletRequest request, HttpServletResponse response) {
        Result<Object> result = new Result<>();
        String productName = product.getProductName();
        String price = product.getPrice();
        String pic = product.getPic();
        Integer brandId = product.getBrandId();
        Integer typeId = product.getTypeId();
        Product newProduct = new Product();
        newProduct.setProductName(productName);
        newProduct.setPrice(price);
        newProduct.setPic(pic);
        newProduct.setBrandId(brandId);
        newProduct.setTypeId(typeId);
        productService.insert(newProduct);
        result.setCode("200");
        result.setMessage("添加成功！");
        return result;
    }

    @RequestMapping(value = "/findDetail", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> findDetail(@RequestBody PageRequest pageQuery, Model model) {
        Result<Object> result = new Result<>();
        PageResult page = productService.findPage(pageQuery);
//        model.addAttribute("page", page);
        result.setData(page);
        return result;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object>  findById(@RequestBody Product product, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        Result<Object> result = new Result<>();
        Integer id = product.getId();
        Product productList = productService.selectById(id);
        result.setData(productList);
        return result;
    }

    @RequestMapping(value = "/delDetail", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> delDetail(@RequestBody Product product, HttpServletRequest request, HttpServletResponse response) {
        Result<Object> result = new Result<>();
        Integer id = product.getId();
        productService.deleteById(id);
        result.setCode("200");
        result.setMessage("删除成功！");
        return result;
    }

    @RequestMapping(value = "/updateDetail", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> updateDetail(@RequestBody Product product, HttpServletRequest request, HttpServletResponse response) {
        Result<Object> result = new Result<>();
        productService.update(product);
        result.setCode("200");
        result.setMessage("修改成功！");
        return result;
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
