package com.qxt.bysj.dao;

import com.qxt.bysj.domain.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
    Tag selectByTagname(String tagname);
    List<Tag> firstTagChoose();
    List<Tag> findRankTag(Integer objType);
}