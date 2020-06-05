package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.OpinionMapper;
import com.qxt.bysj.domain.Opinion;
import com.qxt.bysj.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpinionServiceImpl extends BaseServiceImpl<Opinion> implements OpinionService {
    @Autowired
    private OpinionMapper opinionDao;
}
