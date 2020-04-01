package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.ReplyMapper;
import com.qxt.bysj.domain.Reply;
import com.qxt.bysj.service.ReplyService;
import com.qxt.bysj.service.UserService;
import com.qxt.bysj.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl extends BaseServiceImpl<Reply> implements ReplyService {
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private VideoService videoService;

}
