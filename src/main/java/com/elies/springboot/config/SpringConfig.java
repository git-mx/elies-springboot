package com.elies.springboot.config;

/**
 * @author 牟雪
 * @since 2018/4/12
 */
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.elies.springboot.controller.UserController;
import com.elies.springboot.datasource.DBProperties;
import com.elies.springboot.datasource.DynamicDataSource;
import com.elies.springboot.datasource.DynamicDataSourceHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


@Configuration // 通过该注解来表明该类是一个Spring的配置，相当于一个xml文件
@ComponentScan(basePackages = "com.elies.springboot") // 配置扫描包
//@PropertySource(value = { "classpath:db.properties"}, ignoreResourceNotFound = true)
public class SpringConfig {
    private static Logger log = LoggerFactory.getLogger(UserController.class);

//    @Value("${jdbc.url}")
//    private String jdbcUrl;
//
//    @Value("${jdbc.driverClassName}")
//    private String jdbcDriverClassName;
//
//    @Value("${jdbc.username}")
//    private String jdbcUsername;
//
//    @Value("${jdbc.password}")
//    private String jdbcPassword;
//
//    @Bean(destroyMethod = "close")
//    public DataSource dataSource() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setDriverClassName(jdbcDriverClassName);
//        druidDataSource.setUrl(jdbcUrl);
//        druidDataSource.setUsername(jdbcUsername);
//        druidDataSource.setPassword(jdbcPassword);
//        return druidDataSource;
//    }

    private AtomicInteger counter = new AtomicInteger(1);

    @Autowired
    DBProperties dbProperties;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("write", dbProperties.getWrite());
        targetDataSources.put("read1", dbProperties.getRead1());
        targetDataSources.put("read2", dbProperties.getRead2());
        targetDataSources.put("read3", dbProperties.getRead3());
        targetDataSources.put("read4", dbProperties.getRead4());
        //采用是想AbstractRoutingDataSource的对象包装多数据源
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        //设置默认的数据源，当拿不到数据源时，使用此配置
        dataSource.setDefaultTargetDataSource(dbProperties.getWrite());
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}