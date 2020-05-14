package com.qxt.bysj.domain;

public class ProductTaste {
    private Integer id;

    private String tastename;

    private Integer hot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTastename() {
        return tastename;
    }

    public void setTastename(String tastename) {
        this.tastename = tastename == null ? null : tastename.trim();
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }
}