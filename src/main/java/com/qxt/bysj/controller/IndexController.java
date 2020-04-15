package com.qxt.bysj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String hello(ModelMap modelMap, @RequestParam(value = "name", required = false) String name) {
        name = "QXT";
        modelMap.put("name", name);
        return "page/index";
    }

    @RequestMapping(value = "/basicTable",method = RequestMethod.GET)
    public String basicTable(ModelMap modelMap, @RequestParam(value = "name", required = false) String name) {
        name = "QXT";
        modelMap.put("name", name);
        return "page/Example/basic-table";
    }
}
