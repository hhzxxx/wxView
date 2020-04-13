package com.qxt.bysj.dao;

import com.qxt.bysj.domain.Article;
import com.qxt.bysj.domain.ObjXuser;

import java.util.List;
import java.util.Map;

public interface ObjXuserMapper extends BaseMapper<ObjXuser> {
    /**
     * 收藏 历史 ，文章和咨询集合
     * @param map
     * @return
     */
    List<ObjXuser> findOldAndCollectPage(Map<String, Object> map);
}