package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.TagXarticleMapper;
import com.qxt.bysj.domain.Article;
import com.qxt.bysj.domain.Tag;
import com.qxt.bysj.domain.TagXarticle;
import com.qxt.bysj.service.TagXarticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagXarticleServiceImpl extends BaseServiceImpl<TagXarticle> implements TagXarticleService {
    @Autowired
    private TagXarticleMapper tagXarticleDao;

    @Override
    public int dealTaskTagXarticle(Tag tag, Article article) {
        TagXarticle obj = new TagXarticle();
        obj.setTagid(tag.getId());
        obj.setArticleid(article.getId());
        try{
            tagXarticleDao.insert(obj);
        }catch (Exception e){
            return 0;
        }
        return 1;
    }
}
