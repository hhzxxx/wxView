package com.qxt.bysj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller//以json格式输出
public class IndexController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String hello(ModelMap modelMap) {
        return "index";   //"page/index.html"
    }

//    @RequestMapping(value = "/hobbySurvey",method = RequestMethod.GET)
//    public String hobbySurvey(ModelMap modelMap) {
//        return "page/hobbySurvey";
//    }
}
