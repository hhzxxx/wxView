package com.qxt.bysj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qxt.bysj.dao.OwnerMapper;
import com.qxt.bysj.domain.Owner;
import com.qxt.bysj.service.OwnerService;
import com.qxt.bysj.utils.BiliRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl extends BaseServiceImpl<Owner> implements OwnerService {
    @Autowired
    private OwnerMapper ownerMapper;
    @Autowired
    private BiliRequest biliRequest;

    @Override
    public Owner selectByOwnerId(Integer ownerId) {
        return ownerMapper.selectByOwnerId(ownerId);
    }

    @Override
    public  void dealOwner(Integer ownerId,int num){
        if(ownerId!=null){
            Owner owner = ownerMapper.selectByOwnerId(ownerId);
            if(owner == null){
                synchronized(this){
                    try
                    {
                        System.out.println("已锁住,处理ownerID："+ownerId+",剩余:"+num+"条");
                        Thread.sleep(5000);
                        String obj =  biliRequest.getHTMLContentByHttpGetMethod("https://api.bilibili.com/x/space/acc/info?jsonp=jsonp&mid="+ownerId,null);
                        if(obj!=null){
                            JSONObject jsonObject = JSON.parseObject(obj).getJSONObject("data");
                            owner = new Owner();
                            owner.setBirthday(jsonObject.getString("birthday"));
                            owner.setFace(jsonObject.getString("face"));
                            owner.setOwner(jsonObject.getString("name"));
                            owner.setOwnerid(ownerId);
                            owner.setSex(jsonObject.getString("sex"));
                            owner.setSign(jsonObject.getString("sign"));
                            ownerMapper.insert(owner);
                        }
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        System.out.println("解锁");
                    }
                }
            }
        }
    }

}
