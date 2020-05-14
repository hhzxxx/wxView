package com.qxt.bysj.controller;

import com.qxt.bysj.domain.*;
import com.qxt.bysj.domain.dto.PageRequest;
import com.qxt.bysj.domain.dto.PageResult;
import com.qxt.bysj.domain.dto.RuleRequest;
import com.qxt.bysj.domain.dto.ruleDto;
import com.qxt.bysj.service.BaseService;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseController<T, S extends BaseService<T>> {
    protected abstract S service();

    @RequestMapping(value = "/findPage", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> findPage(@RequestBody PageRequest pageQuery, Model model) {
        Result<Object> result = new Result<>();
        PageResult page = this.service().findPage(pageQuery);
        result.setData(page);
        return result;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> find(@RequestBody RuleRequest ruleQuery, Model model) {
        Result<Object> result = new Result<>();
        Map<String,Object> map = new HashMap<>();
        List<ruleDto> ruleList = ruleQuery.getRules();
        ruleList.forEach(dto -> map.put(dto.getRuleName(),dto.getRuleValue()));
        List<T> resList = this.service().find(map);
        result.setData(resList);
        return result;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> get(@RequestParam(value = "id",required = false) Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        Result<Object> result = new Result<>();
        T entity = this.service().selectById(id);
        result.setData(entity);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> delete(@RequestParam(value = "id",required = false) Integer id, HttpServletRequest request, HttpServletResponse response) {
        Result<Object> result = new Result<>();
        this.service().deleteById(id);
        result.setCode("200");
        result.setMessage("删除成功！");
        return result;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> insert(@RequestBody T entity, HttpServletRequest request, HttpServletResponse response, Model model) {
        Result<Object> result = new Result<>();
        this.service().insert(entity);
        result.setCode("200");
        result.setMessage("保存成功！");
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> update(@RequestBody T entity, HttpServletRequest request, HttpServletResponse response, Model model) {
        Result<Object> result = new Result<>();
        this.service().update(entity);
        result.setCode("200");
        result.setMessage("保存成功！");
        return result;
    }
}
