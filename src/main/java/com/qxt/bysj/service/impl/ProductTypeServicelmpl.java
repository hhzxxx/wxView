package com.qxt.bysj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxt.bysj.dao.ProductTypeMapper;
import com.qxt.bysj.domain.PageRequest;
import com.qxt.bysj.domain.ProductType;
import com.qxt.bysj.domain.dto.ruleDto;
import com.qxt.bysj.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductTypeServicelmpl extends BaseServiceImpl<ProductType> implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeDao;

}
