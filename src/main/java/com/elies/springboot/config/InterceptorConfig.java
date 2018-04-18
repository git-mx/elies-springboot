package com.elies.springboot.config;

import com.elies.springboot.common.InterceptorUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 牟雪
 * @since 2018/4/18
 */

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePaths = new ArrayList<>();
        //swaggerui的访问路径
        excludePaths.add("/swagger*/**");
        excludePaths.add("/v2/**");
        excludePaths.add("/webjars/**");
        registry.addInterceptor(new InterceptorUtil())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePaths);
    }

}
