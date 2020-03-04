package com.qxt.bysj.utils;

import com.qxt.bysj.domain.dto.ruleDto;

import java.util.List;

/**
 * @Author huanghz
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
