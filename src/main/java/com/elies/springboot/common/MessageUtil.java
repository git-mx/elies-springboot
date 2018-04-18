package com.elies.springboot.common;

/**
 * 消息转换基础类
 *
 * @author 牟雪
 * @since 2018/4/2
 */

import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class MessageUtil {
    private static final String PATTERN = "\\$\\{.*\\}";
    private String Code;
    private String message;

    public MessageUtil(){

    }
    public MessageUtil(String code, String message){
        this.setCode(code);
        this.setMessage(message);
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage(String paramValue){
        return StringUtils.replacePattern(this.message, this.PATTERN, paramValue);
    }

    public String getMessage(Map<String, String> paramValues){
        String msg = this.message;
        Entry entry;
        Iterator iterator = paramValues.entrySet().iterator();
        while(iterator.hasNext()){
            entry = (Entry)iterator.next();
            msg = StringUtils.replacePattern(msg, "\\$\\{" + entry.getKey().toString() + "\\}", entry.getValue().toString());
        }
        return msg;
    }
}
