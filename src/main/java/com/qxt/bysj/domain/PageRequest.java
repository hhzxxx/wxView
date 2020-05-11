package com.qxt.bysj.domain;

import com.qxt.bysj.domain.dto.ruleDto;

import java.util.List;

/**
 * @Author qxt
 * @Date 2020/3/4 10:36
 * @Version 1.0
 */
public class PageRequest {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;

    private List<ruleDto> rules;

    private String order;  //排序字段
    private String orderType;  //排序类型

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public List<ruleDto> getRules() {
        return rules;
    }

    public void setRules(List<ruleDto> rules) {
        this.rules = rules;
    }

    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
