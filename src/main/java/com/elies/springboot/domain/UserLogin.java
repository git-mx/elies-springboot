package com.elies.springboot.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author 牟雪
 * @since 2018/4/3
 */
@ApiModel(value="用户登录POST请求实体")
public class UserLogin {
    @ApiModelProperty(value="登录名")
    @NotNull
    private String loginName;

    @ApiModelProperty(value="登录密码")
    @NotNull
    private String loginPassword;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
