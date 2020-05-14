package com.qxt.bysj.domain;

public class ProductTemperature {
    private Integer id;

    private String temperaturename;

    private Integer hot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemperaturename() {
        return temperaturename;
    }

    public void setTemperaturename(String temperaturename) {
        this.temperaturename = temperaturename == null ? null : temperaturename.trim();
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }
}