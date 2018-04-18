package com.elies.springboot.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 牟雪
 * @since 2018/4/13
 */
@Component
@ConfigurationProperties(prefix = "druid")
public class DBProperties {

    private DruidDataSource write;
    private DruidDataSource read1;
    private DruidDataSource read2;
    private DruidDataSource read3;
    private DruidDataSource read4;

    public DruidDataSource getWrite() {
        return write;
    }

    public void setWrite(DruidDataSource write) {
        this.write = write;
    }

    public DruidDataSource getRead1() {
        return read1;
    }

    public void setRead1(DruidDataSource read1) {
        this.read1 = read1;
    }

    public DruidDataSource getRead2() {
        return read2;
    }

    public void setRead2(DruidDataSource read2) {
        this.read2 = read2;
    }

    public DruidDataSource getRead3() {
        return read3;
    }

    public void setRead3(DruidDataSource read3) {
        this.read3 = read3;
    }

    public DruidDataSource getRead4() {
        return read4;
    }

    public void setRead4(DruidDataSource read4) {
        this.read4 = read4;
    }
}
