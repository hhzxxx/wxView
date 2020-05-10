package com.qxt.bysj.domain;

import java.util.Date;

public class UpFile {
    private Integer id;

    private String uuid;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    private String orifile;

    private Integer orisize;

    private String storagefile;

    private Integer filesize;

    private String remark;

    private String objecttype;

    private Integer objectid;

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

    public String getOrifile() {
        return orifile;
    }

    public void setOrifile(String orifile) {
        this.orifile = orifile == null ? null : orifile.trim();
    }

    public Integer getOrisize() {
        return orisize;
    }

    public void setOrisize(Integer orisize) {
        this.orisize = orisize;
    }

    public String getStoragefile() {
        return storagefile;
    }

    public void setStoragefile(String storagefile) {
        this.storagefile = storagefile == null ? null : storagefile.trim();
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getObjecttype() {
        return objecttype;
    }

    public void setObjecttype(String objecttype) {
        this.objecttype = objecttype == null ? null : objecttype.trim();
    }

    public Integer getObjectid() {
        return objectid;
    }

    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }
}