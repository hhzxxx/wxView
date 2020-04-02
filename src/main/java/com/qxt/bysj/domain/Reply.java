package com.qxt.bysj.domain;

import java.util.Date;

public class Reply {
    private Integer id;

    private String uuid;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    private Integer userid;

    private String content;

    private String objtype;

    private Integer objid;

    private Integer good;

    private Integer bad;

    //非DB
    private String nickName;
    private Integer isBad;
    private Integer isGood;

    public Integer getIsBad() {
        return isBad;
    }

    public void setIsBad(Integer isBad) {
        this.isBad = isBad;
    }

    public Integer getIsGood() {
        return isGood;
    }

    public void setIsGood(Integer isGood) {
        this.isGood = isGood;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getObjtype() {
        return objtype;
    }

    public void setObjtype(String objtype) {
        this.objtype = objtype == null ? null : objtype.trim();
    }

    public Integer getObjid() {
        return objid;
    }

    public void setObjid(Integer objid) {
        this.objid = objid;
    }

    public Integer getGood() {
        return good;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    public Integer getBad() {
        return bad;
    }

    public void setBad(Integer bad) {
        this.bad = bad;
    }
}