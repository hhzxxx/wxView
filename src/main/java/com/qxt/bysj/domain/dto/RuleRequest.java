package com.qxt.bysj.domain.dto;

import java.util.List;

public class RuleRequest {
    private List<ruleDto> rules;

    public List<ruleDto> getRules() {
        return rules;
    }

    public void setRules(List<ruleDto> rules) {
        this.rules = rules;
    }
}
