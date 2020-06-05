package com.qxt.bysj.domain;

public class Opinion {
    private Integer id;
    private String userId;
    private String opinionTitle;
    private String opinionContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpinionTitle() {
        return opinionTitle;
    }

    public void setOpinionTitle(String opinionTitle) {
        this.opinionTitle = opinionTitle;
    }

    public String getOpinionContent() {
        return opinionContent;
    }

    public void setOpinionContent(String opinionContent) {
        this.opinionContent = opinionContent;
    }

}
