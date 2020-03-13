package com.qxt.bysj.domain;

import java.util.Date;

public class VideoXuser {
    private Integer id;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    private Integer videoid;

    private Integer userid;

    private Integer isgood;

    private Integer isbad;

    private Integer iscollection;

    private Date collectiontime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getVideoid() {
        return videoid;
    }

    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getIsgood() {
        return isgood;
    }

    public void setIsgood(Integer isgood) {
        this.isgood = isgood;
    }

    public Integer getIsbad() {
        return isbad;
    }

    public void setIsbad(Integer isbad) {
        this.isbad = isbad;
    }

    public Integer getIscollection() {
        return iscollection;
    }

    public void setIscollection(Integer iscollection) {
        this.iscollection = iscollection;
    }

    public Date getCollectiontime() {
        return collectiontime;
    }

    public void setCollectiontime(Date collectiontime) {
        this.collectiontime = collectiontime;
    }
}