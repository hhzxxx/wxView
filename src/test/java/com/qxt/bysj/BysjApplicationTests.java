package com.qxt.bysj;

import com.qxt.bysj.domain.Article;
import com.qxt.bysj.domain.Video;
import com.qxt.bysj.service.ArticleService;
import com.qxt.bysj.service.OwnerService;
import com.qxt.bysj.service.UserService;
import com.qxt.bysj.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BysjApplicationTests {
    @Autowired
    private VideoService videoService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private OwnerService ownerService;
    @Test
    void contextLoads() {
        Map<String, Object> map = new HashMap<>();
        List<Video> list = videoService.find(map);
//        List<Article> list = articleService.find(map);
        int num = list.size();
        for(Video obj:list){
            ownerService.dealOwner(obj.getOwnerid(),num);
            num--;
        }
    }

}
