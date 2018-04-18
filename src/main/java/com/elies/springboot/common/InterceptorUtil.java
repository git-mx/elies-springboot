package com.elies.springboot.common;

import com.alibaba.fastjson.JSON;
import com.elies.springboot.constant.Constant;
import com.elies.springboot.constant.ResponseMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author 牟雪
 * @since 2018/4/18
 */
public class InterceptorUtil implements HandlerInterceptor {
    private static Logger log = LoggerFactory.getLogger(InterceptorUtil.class);
    @Autowired
    RedisService redisService;

    public  boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        if(!(handler instanceof HandlerMethod)){
            return true;
        }else{
            String sessionId = request.getHeader("sessionId");
            if(StringUtils.isBlank(sessionId)){
                ResponseUtil message = ResponseUtil.fail(ResponseMessage.PARAM_ERROR.getCode(), ResponseMessage.PARAM_ERROR.getMessage());
                this.writeMessage(response, message);
                return false;
            }else{
                String sessionStr = redisService.get(String.format("SESSIONID:%s", sessionId));
                if(null == sessionStr){
                    ResponseUtil message = ResponseUtil.fail(ResponseMessage.SESSION_TIMEOUT.getCode(), ResponseMessage.SESSION_TIMEOUT.getMessage());
                    this.writeMessage(response, message);
                    return false;
                }else{
                    redisService.expire(String.format("SESSIONID:%s", sessionId), Constant.SESSION_EXPIRE.intValue());
                    return true;
                }
            }
        }
    }

    private void writeMessage(HttpServletResponse response, ResponseUtil message) throws IOException {
        response.getWriter().write(JSON.toJSONString(message));
        response.getWriter().flush();
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

    }
}
