package com.qxt.bysj.domain;

public class ProductBrand {
    private Integer id;

    private String brandname;

    private String brandpic;

    private String introduction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname == null ? null : brandname.trim();
    }

    public String getBrandpic() {
        return brandpic;
    }

    public void setBrandpic(String brandpic) {
        this.brandpic = brandpic == null ? null : brandpic.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}