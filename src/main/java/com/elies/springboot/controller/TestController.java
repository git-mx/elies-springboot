package com.elies.springboot.controller;

import com.elies.springboot.common.ResponseUtil;
import com.elies.springboot.constant.ResponseMessage;
import com.elies.springboot.domain.UserLogin;
import com.elies.springboot.domain.UserQuery;
import com.elies.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户对外接口
 *
 * @author 牟雪
 * @since 2018/4/3
 */
@RestController
@RequestMapping("/test")
@Api(value="测试接口", description="测试接口")
public class TestController {
    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value="用户登录",httpMethod="POST",notes="用户登录")
    @PostMapping(value="/testLogin")
    public ResponseUtil testLogin(
            @ApiParam(required=true,name="userLogin",value="POST对象") @RequestBody @Validated UserLogin userLogin
    ){
        try{
            return ResponseUtil.success(userService.userLogin(userLogin));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtil.fail(ResponseMessage.RETURN_CODE_SERVER_ERROR.getCode(),
                    ResponseMessage.RETURN_CODE_SERVER_ERROR.getMessage());
        }
    }

    @ApiOperation(value="用户查询列表",httpMethod="POST",notes="用户查询列表")
    @PostMapping(value="/listTest")
    public ResponseUtil listTest(
            @ApiParam(required=true,name="userQuery",value="查询条件") @RequestBody @Validated UserQuery userQuery
    ){
        try{
            return ResponseUtil.success(userService.listUser(userQuery));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtil.fail(ResponseMessage.RETURN_CODE_SERVER_ERROR.getCode(),
                    ResponseMessage.RETURN_CODE_SERVER_ERROR.getMessage());
        }
    }

}
