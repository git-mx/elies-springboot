//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.elies.springboot.common;

import com.elies.springboot.constant.ResponseMessage;

import java.io.Serializable;

public class ResponseUtil<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private MessageUtil meta;
    private T data;

    public ResponseUtil(T data) {
        this(ResponseMessage.SUCCESS.getCode(), ResponseMessage.SUCCESS.getMessage(), data);
    }

    public ResponseUtil() {
        this.meta = new MessageUtil();
    }

    public void setMeta(MessageUtil meta) {
        this.meta = meta;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseUtil(String code, String message) {
        this(code, message, null);
    }

    public ResponseUtil(String code, String message, T data) {
        this.meta = new MessageUtil();
        this.meta.setCode(code);
        this.meta.setMessage(message);
        this.data = data;
    }

    public ResponseUtil(ExceptionUtil be) {
        this(be.getCode(), be.getMessage(), null);
    }

    public ResponseUtil(MessageUtil messageUtil) {
        this(messageUtil.getCode(), messageUtil.getMessage(), null);
    }

    public static <T> ResponseUtil<T> success(T data) {
        return new ResponseUtil(data);
    }

    public static <T> ResponseUtil<T> fail(String code, String message) {
        return new ResponseUtil(code, message);
    }

    public T getData() {
        return this.data;
    }

    public MessageUtil getMeta() {
        return this.meta;
    }

    public boolean checkSuccess() {
        return null != this.meta && ResponseMessage.RETURN_CODE_SUCCESS.getCode().equals(this.meta.getCode());
    }
}
