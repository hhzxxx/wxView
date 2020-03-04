package com.qxt.bysj.domain.dto;

/**
 * @Author huanghz
 * @Date 2020/3/4 11:21
 * @Version 1.0
 */
public class ruleDto {
    private String ruleName;

    private Object ruleValue;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Object getRuleValue() {
        return ruleValue;
    }

    public void setRuleValue(Object ruleValue) {
        this.ruleValue = ruleValue;
    }
}
