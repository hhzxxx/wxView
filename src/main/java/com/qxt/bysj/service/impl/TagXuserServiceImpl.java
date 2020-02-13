package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.TagXuserMapper;
import com.qxt.bysj.domain.TagXuser;
import com.qxt.bysj.service.TagXuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagXuserServiceImpl extends BaseServiceImpl<TagXuser> implements TagXuserService {
    @Autowired
    private TagXuserMapper TagXuserDao;

}
