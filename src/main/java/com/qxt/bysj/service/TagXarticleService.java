package com.qxt.bysj.service;

import com.qxt.bysj.domain.Article;
import com.qxt.bysj.domain.Tag;
import com.qxt.bysj.domain.TagXarticle;

/**
 * @Author qxt
 * @Date 2020/2/13 19:10
 * @Version 1.0
 */
public interface TagXarticleService extends BaseService<TagXarticle> {

    int dealTaskTagXarticle(Tag tag, Article article);
}
