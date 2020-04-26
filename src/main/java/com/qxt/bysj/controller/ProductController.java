package com.qxt.bysj.controller;

import com.qxt.bysj.domain.Product;
import com.qxt.bysj.service.ProductService;
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
import java.util.List;

@Controller//以json格式输出
public class ProductController {
    @Autowired
    @Resource
    private ProductService productService;

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
        Product newProduct = new Product();
        newProduct.setProductName(productName);
        newProduct.setPrice(price);
        newProduct.setPic(pic);
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
        model.addAttribute("page", page);
        result.setData(page);
        return result;
    }
}
