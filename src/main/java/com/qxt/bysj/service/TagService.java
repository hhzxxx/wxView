package com.qxt.bysj.service;

import com.qxt.bysj.domain.Tag;
import com.qxt.bysj.domain.Video;

/**
 * @Author huanghz
 * @Date 2020/2/13 19:10
 * @Version 1.0
 */
public interface TagService extends BaseService<Tag> {

    int dealTaskVideoTag(Video video);

    Tag selectByTagname(String tagname);

}
