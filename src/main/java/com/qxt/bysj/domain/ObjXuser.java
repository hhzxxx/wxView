package com.qxt.bysj.domain;

import java.util.Date;

public class ObjXuser {
    private Integer id;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    private Integer objid;

    private Integer objtype;

    private Integer userid;

    private Integer isgood;

    private Integer isbad;

    private Integer iscollection;

    private Date collectiontime;

    //非DB
    private String title;
    private Integer avid;
    private Integer cid;
    private String owner;
    private Integer ownerid;
    private String pic;
    private String remark;
    private Integer hot;
    private Integer good;
    private Integer bad;
    private Integer collection;

    public Integer getObjtype() {
        return objtype;
    }

    public void setObjtype(Integer objtype) {
        this.objtype = objtype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAvid() {
        return avid;
    }

    public void setAvid(Integer avid) {
        this.avid = avid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(Integer ownerid) {
        this.ownerid = ownerid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
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

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

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

    public Integer getObjid() {
        return objid;
    }

    public void setObjid(Integer objid) {
        this.objid = objid;
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