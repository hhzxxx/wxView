package com.qxt.bysj.dao;

import com.qxt.bysj.domain.Tag;

public interface TagMapper extends BaseMapper<Tag> {
    Tag selectByTagname(String tagname);
}