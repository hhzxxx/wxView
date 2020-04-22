package com.qxt.bysj.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller//以json格式输出
public class IndexController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String hello(ModelMap modelMap, @RequestParam(value = "name", required = false) String name) {
        name = "QXT";
        modelMap.put("name", name);
        return "page/index";   //"page/index.html"
    }

    @RequestMapping(value = "/basicTable",method = RequestMethod.GET)
    public String basicTable(ModelMap modelMap, @RequestParam(value = "name", required = false) String name) {
        name = "QXT";
        modelMap.put("name", name);
        return "page/Example/basic-table";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(ModelMap modelMap, @RequestParam(value = "name", required = false) String name) {
        name = "QXT";
        modelMap.put("name", name);
        return "page/login";
    }
}
