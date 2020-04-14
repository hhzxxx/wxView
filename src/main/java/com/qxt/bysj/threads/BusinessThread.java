package com.qxt.bysj.threads;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author qxt
 * @Date 2020/3/12 16:10
 * @Version 1.0
 */
@Component
@Scope("prototype")//spring 多例
public class BusinessThread implements Runnable{

    private Integer objId;
    private Integer objType;
    private String openId;
    private String acceptStr;
    private BusinessService businessService;

    public BusinessThread(String acceptStr,String openId,Integer objId,Integer objType,BusinessService businessService) {
        this.openId = openId;
        this.objId = objId;
        this.objType = objType;
        this.acceptStr = acceptStr;
        this.businessService = businessService;
    }

    public String getAcceptStr() {
        return acceptStr;
    }

    public void setAcceptStr(String acceptStr) {
        this.acceptStr = acceptStr;
    }

    public Integer getObjId() {
        return objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }

    public Integer getObjType() {
        return objType;
    }

    public void setObjType(Integer objType) {
        this.objType = objType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public void run() {
        //业务操作
        System.out.println("多线程已经处理订单插入系统，订单号："+acceptStr);

//        BusinessService businessService = (BusinessService) SpringContextUtils.getBean(BusinessService.class);

        businessService.doVideoTap(openId,objId,objType);

    }
}
