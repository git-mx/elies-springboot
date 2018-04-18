package com.elies.springboot.domain;

import com.elies.springboot.common.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 用户列表页查询条件
 *
 * @author 牟雪
 * @since 2018/4/3
 */
@ApiModel("用户列表页查询条件")
public class UserQuery extends BaseQuery {


    @ApiModelProperty(value="姓名")
    private String userName;

    @ApiModelProperty(value="性别")
    private Integer userSex;

    @ApiModelProperty(value="年龄下限")
    private Integer lowerAge;

    @ApiModelProperty(value="年龄上限")
    private Integer upperAge;

    @ApiModelProperty(value="地址")
    private String userAddress;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Integer getLowerAge() {
        return lowerAge;
    }

    public void setLowerAge(Integer lowerAge) {
        this.lowerAge = lowerAge;
    }

    public Integer getUpperAge() {
        return upperAge;
    }

    public void setUpperAge(Integer upperAge) {
        this.upperAge = upperAge;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
