package com.elies.springboot.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 牟雪
 * @since 2018/4/13
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);

    @Autowired
    DBProperties dbProperties;

    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    protected Object determineCurrentLookupKey() {
        //从共享线程中获取数据源名称
        if(DynamicDataSourceHolder.isChoiceWrite()) {
            log.debug("current determine write datasource");
            return "write";
        }

        if(DynamicDataSourceHolder.isChoiceNone()) {
            log.debug("no choice read/write, default determine write datasource");
            return "write";
        }
        return determineReadDataSource();
    }



    private String determineReadDataSource() {
        //实现简单的负载均衡
        int tempInt = counter.incrementAndGet();
        int index = tempInt % 4;
        if(index < 0) {
            index = - index;
        }else if(index == 0){
            index = 4;
        }

        log.debug("current determine read datasource : datasource" + index);

        return String.format("read%s", String.valueOf(index));
    }

}
