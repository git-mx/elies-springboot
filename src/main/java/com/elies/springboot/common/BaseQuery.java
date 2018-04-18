package com.elies.springboot.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author 牟雪
 * @since 2018/4/3
 */
@ApiModel("分页信息基础类")
public class BaseQuery {
    @ApiModelProperty(value="当前页")
    @NotNull
    private Integer pageIndex;

    @ApiModelProperty(value="每页数据条数")
    @NotNull
    private Integer pageSize;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
