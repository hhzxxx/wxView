package com.qxt.bysj.domain;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 119151750216961690L;
    private String code;
    private String message = "";
    private T data;

    public Result() {
        this.code = "0";
    }

    public Result(T data) {
        this.code = "0";
        this.data = data;
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(boolean error, String code, String message) {
        if (error) {
            this.code = code;
            this.message = message;
        } else {
            this.code = "0";
        }

    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
