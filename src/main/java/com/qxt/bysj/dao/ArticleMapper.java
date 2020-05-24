package com.qxt.bysj.dao;

import com.qxt.bysj.domain.Article;

import java.util.List;
import java.util.Map;

public interface ArticleMapper extends BaseMapper<Article> {

    Article selectByCvid(Integer cvid);

    /**
     * 首页 通过个人标签获取
     * @param map
     * @return
     */
    List<Article> findIndexPage(Map<String, Object> map);

    List<Article> findPageOrder(Map<String, Object> map);
}