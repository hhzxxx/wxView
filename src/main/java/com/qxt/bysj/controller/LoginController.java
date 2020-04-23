package com.qxt.bysj.controller;

import com.qxt.bysj.domain.Manager;
import com.qxt.bysj.domain.SessionUser;
import com.qxt.bysj.service.ManagerService;
import com.qxt.bysj.utils.IpUtil;
import com.qxt.bysj.utils.MD5Utils;
import com.qxt.bysj.utils.Result;
import com.qxt.bysj.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class LoginController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        Manager manager=(Manager)request.getSession().getAttribute("MANAGER");
        if (manager == null) {
            return "/login";
        }
        return "redirect:" + MessageFormat.format("/index",new Object());
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> postLogin(@RequestBody Manager manager, HttpServletRequest request, HttpServletResponse response) {
        Result<Object> result = new Result<>();
        String account = manager.getAccount();
        String passWord = manager.getPassword();
        String md5PassWord = MD5Utils.stringToMD5(account+passWord);
        Manager entity = managerService.selectByAccount(account);
        if(entity!=null){
            if(entity.getPassword().equals(md5PassWord)){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化为年月
                String ipAddress = IpUtil.getIpAddr(request);
                SessionUser sessionUser = new SessionUser();
                sessionUser.setAccount(account);
                sessionUser.setLoginIp(ipAddress);
                sessionUser.setLoginTime(sdf.format(new Date()));
                sessionUser.setName(entity.getName());
                Session.setSessionObject("SessionUser",sessionUser);
                result.setMessage("登陆成功！");
                result.setCode("200");
                return result;
            }
        }
        result.setMessage("用户名或密码错误！");
        result.setCode("99");
        return result;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> register(@RequestBody Manager manager, HttpServletRequest request, HttpServletResponse response) {
        Result<Object> result = new Result<>();
        String account = manager.getAccount();
        Manager entity = managerService.selectByAccount(account);
        if(entity!=null){
            result.setCode("99");
            result.setMessage("账号已存在！");
            return result;
        }
        String passWord = manager.getPassword();
        String md5PassWord = MD5Utils.stringToMD5(account+passWord);
        Manager newManeger = new Manager();
        newManeger.setAccount(account);
        newManeger.setPassword(md5PassWord);
        newManeger.setName(manager.getName());
        newManeger.setUuid(UUID.randomUUID().toString());
        managerService.insert(newManeger);
        result.setCode("200");
        result.setMessage("注册成功！");
        return result;
    }

}
