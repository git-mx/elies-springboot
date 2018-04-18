package com.elies.springboot.constant;


/**
 * @author 牟雪
 * @since 2018/4/2
 */

import com.elies.springboot.common.MessageUtil;

public enum ResponseMessage {
    SUCCESS("0", "成功", "SUCCESS"),
    FAIL("1", "失败", "FAIL");
    private String code;
    private String name;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private ResponseMessage(String code, String name, String message){
        this.code = code;
        this.name = name;
        this.message = message;
    }
    public static final String SUCCESS_CODE = "0";
    public static final String FAIL_CODE = "1";
    public static final MessageUtil RETURN_CODE_SUCCESS = new MessageUtil("0", "成功");
    public static final MessageUtil RETURN_CODE_FALL = new MessageUtil("1", "失败");
    public static final MessageUtil RETURN_CODE_UNKNOWN_ERROR = new MessageUtil("3", "未知异常");
    public static final MessageUtil RETURN_CODE_SERVER_ERROR = new MessageUtil("4", "内部服务器错误");
    public static final MessageUtil RETURN_CODE_PARAM_IS_NULL = new MessageUtil("5", "参数${paramName}为空！");
    public static final MessageUtil RETURN_CODE_PARAM_IS_INVALID = new MessageUtil("6", "参数${paramName}无效！");
}
