package com.qxt.bysj.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxt.bysj.dao.ArticleMapper;
import com.qxt.bysj.domain.*;
import com.qxt.bysj.domain.dto.PageRequest;
import com.qxt.bysj.domain.dto.PageResult;
import com.qxt.bysj.domain.dto.ruleDto;
import com.qxt.bysj.service.ArticleService;
import com.qxt.bysj.service.OwnerService;
import com.qxt.bysj.service.TagService;
import com.qxt.bysj.utils.DateUtil;
import com.qxt.bysj.utils.EmojiFilter;
import com.qxt.bysj.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {
    @Autowired
    private ArticleMapper ArticleDao;
    @Autowired
    private TagService tagService;
    @Autowired
    private OwnerService ownerService;
    @Override
    public Article selectByCvid(Integer cvid) {
        return ArticleDao.selectByCvid(cvid);
    }

    @Override
    public int dealTaskArticle(JSONObject jsonObj,int num){
        if(!jsonObj.containsKey("dynamic")) return 0;
        if(EmojiFilter.containsEmoji(jsonObj.getString("dynamic"))) return 0;
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer cvid = Integer.valueOf(jsonObj.getString("id"));
        Article Article =  new Article();
        Article Article1 = ArticleDao.selectByCvid(cvid);
        int flag = 0;
        if(Article1 == null) {
            flag = 1;
            Article.setUpdatetime(date);
            Article.setStatus(0);
            Article.setCreatetime(date);
            Article.setUuid(UUID.randomUUID().toString());
            Article.setCvid(cvid);
            ArticleDao.insert(Article);
        }
        Article = ArticleDao.selectByCvid(cvid);
        Article.setUpdatetime(date);
        Article.setOwner(jsonObj.getJSONObject("author").get("name").toString());
        Article.setOwnerid((Integer) jsonObj.getJSONObject("author").get("mid"));
        if(jsonObj.getJSONArray("image_urls").size()>0){
            Article.setPic(jsonObj.getJSONArray("image_urls").get(0).toString());
        }
        Article.setTitle(jsonObj.getString("title"));
        Article.setRemark(jsonObj.getString("dynamic"));
        ArticleDao.updateByPrimaryKeySelective(Article);
        ownerService.dealOwner(Article.getOwnerid(),num);
        if(flag == 1){
            tagService.dealTaskArticleTag(Article);
        }
        return 1;
    }

    @Override
    public PageResult findIndexPage(PageRequest pageRequest) {
        PageResult pageResult = PageUtils.getPageResult(pageRequest, getIndexPage(pageRequest));
        List<Article> list = (List<Article>) pageResult.getContent();
        for(Article article : list){
            Owner owner = ownerService.selectByOwnerId(article.getOwnerid());
            if(owner!=null) article.setFace(owner.getFace());
        }
        pageResult.setContent(list);
        return pageResult;
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<Article> getIndexPage(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ruleDto> rules = pageRequest.getRules();
        Map<String, Object> map = new HashMap<>();
        if(rules.size()>0){
            for(int i=0;i<rules.size();i++){
                map.put(rules.get(i).getRuleName(),rules.get(i).getRuleValue());
            }
        }
        List<Article> sysMenus = ArticleDao.findIndexPage(map);
        Date date = new Date();
        for(Article article:sysMenus){
            try {
                article.setCreated(-DateUtil.longOfTwoDate(article.getCreatetime(),date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new PageInfo<Article>(sysMenus);
    }
}
