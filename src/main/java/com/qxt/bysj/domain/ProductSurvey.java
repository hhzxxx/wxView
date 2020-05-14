package com.qxt.bysj.domain;

import java.util.Date;

public class ProductSurvey {
    private Integer id;

    private Date createtime;

    private String openid;

    private String typelist;

    private String brandlist;

    private String tastelist;

    private String templist;

    private String productlist;

    private String remark;


    //非DB
    private Integer[] typelist1;

    private Integer[] brandlist1;

    private Integer[] tastelist1;

    private Integer[] templist1;

    private Integer[] productlist1;

    public Integer[] getTypelist1() {
        return typelist1;
    }

    public void setTypelist1(Integer[] typelist1) {
        this.typelist1 = typelist1;
    }

    public Integer[] getBrandlist1() {
        return brandlist1;
    }

    public void setBrandlist1(Integer[] brandlist1) {
        this.brandlist1 = brandlist1;
    }

    public Integer[] getTastelist1() {
        return tastelist1;
    }

    public void setTastelist1(Integer[] tastelist1) {
        this.tastelist1 = tastelist1;
    }

    public Integer[] getTemplist1() {
        return templist1;
    }

    public void setTemplist1(Integer[] templist1) {
        this.templist1 = templist1;
    }

    public Integer[] getProductlist1() {
        return productlist1;
    }

    public void setProductlist1(Integer[] productlist1) {
        this.productlist1 = productlist1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getTypelist() {
        return typelist;
    }

    public void setTypelist(String typelist) {
        this.typelist = typelist == null ? null : typelist.trim();
    }

    public String getBrandlist() {
        return brandlist;
    }

    public void setBrandlist(String brandlist) {
        this.brandlist = brandlist == null ? null : brandlist.trim();
    }

    public String getTastelist() {
        return tastelist;
    }

    public void setTastelist(String tastelist) {
        this.tastelist = tastelist == null ? null : tastelist.trim();
    }

    public String getTemplist() {
        return templist;
    }

    public void setTemplist(String templist) {
        this.templist = templist == null ? null : templist.trim();
    }

    public String getProductlist() {
        return productlist;
    }

    public void setProductlist(String productlist) {
        this.productlist = productlist == null ? null : productlist.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}