package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.UpFileMapper;
import com.qxt.bysj.domain.UpFile;
import com.qxt.bysj.service.UpFileService;
import com.qxt.bysj.service.UserService;
import com.qxt.bysj.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpFileServiceImpl extends BaseServiceImpl<UpFile> implements UpFileService {
    @Autowired
    private UpFileMapper UpFileMapper;

}
