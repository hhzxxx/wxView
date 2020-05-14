package com.qxt.bysj.controller;

import com.qxt.bysj.domain.Manager;
import com.qxt.bysj.domain.Result;
import com.qxt.bysj.domain.SessionUser;
import com.qxt.bysj.service.ManagerService;
import com.qxt.bysj.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/Profile", method = RequestMethod.POST)
public class ProfileController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/ProfileMain", method = RequestMethod.GET)
    public String ProfileMain() {
        return "page/profile";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> save(@RequestBody Manager manager) {
        Result<Object> result = new Result<>();
        Integer id = manager.getId();
        if(id!=null){
            Manager newManager = managerService.selectById(id);
            newManager.setName(manager.getName());
            newManager.setRemark(manager.getRemark());
            String passWord = manager.getPassword();
            if(passWord!=null && passWord.length()>6){
                String md5PassWord = MD5Utils.stringToMD5(newManager.getAccount()+passWord);
                newManager.setPassword(md5PassWord);
            }
            managerService.update(newManager);
            result.setCode("200");
        }else {
            result.setCode("0");
        }
        return result;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> get(HttpServletRequest request) {
        Result<Object> result = new Result<>();
        SessionUser sessionUser=(SessionUser)request.getSession().getAttribute("SessionUser");
        if(sessionUser!=null){
            String account = sessionUser.getAccount();
            Manager manager = managerService.selectByAccount(account);
            manager.setPassword("");
            result.setData(manager);
            result.setCode("200");
        }else {
            result.setCode("0");
            result.setMessage("未登录！");
        }
        return result;
    }



}
