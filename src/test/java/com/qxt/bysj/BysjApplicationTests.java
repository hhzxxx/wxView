package com.qxt.bysj;

import com.qxt.bysj.domain.Video;
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
    private OwnerService ownerService;
    @Test
    void contextLoads() {
        Map<String, Object> map = new HashMap<>();
        List<Video> list = videoService.find(map);
        int num = list.size();
        for(Video video:list){
            ownerService.dealOwner(video.getOwnerid(),num);
            num--;
        }
    }

}
