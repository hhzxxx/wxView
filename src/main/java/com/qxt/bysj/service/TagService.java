package com.qxt.bysj.service;

import com.qxt.bysj.domain.Article;
import com.qxt.bysj.domain.Tag;
import com.qxt.bysj.domain.Video;

import java.util.List;

/**
 * @Author qxt
 * @Date 2020/2/13 19:10
 * @Version 1.0
 */
public interface TagService extends BaseService<Tag> {

    int dealTaskVideoTag(Video video);

    int dealTaskArticleTag(Article article);


    Tag selectByTagname(String tagname);

    List<Tag> firstTagChoose();
    List<Tag> findRankTag(Integer objType);

}
