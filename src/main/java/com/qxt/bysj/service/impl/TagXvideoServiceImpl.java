package com.qxt.bysj.service.impl;

import com.qxt.bysj.dao.TagXvideoMapper;
import com.qxt.bysj.domain.Tag;
import com.qxt.bysj.domain.TagXvideo;
import com.qxt.bysj.domain.Video;
import com.qxt.bysj.service.TagXvideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagXvideoServiceImpl extends BaseServiceImpl<TagXvideo> implements TagXvideoService {
    @Autowired
    private TagXvideoMapper tagXvideoDao;

    @Override
    public int dealTaskTagXvideo(Tag tag, Video video) {
        TagXvideo obj = new TagXvideo();
        obj.setTagid(tag.getId());
        obj.setVideoid(video.getId());
        try{
            tagXvideoDao.insert(obj);
        }catch (Exception e){
            return 0;
        }
        return 1;
    }
}
