package com.elies.springboot.controller;

import com.elies.springboot.common.RedisService;
import com.elies.springboot.common.ResponseUtil;
import com.elies.springboot.constant.ResponseMessage;
import com.elies.springboot.domain.*;
import com.elies.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 用户对外接口
 *
 * @author 牟雪
 * @since 2018/4/3
 */
@RestController
@RequestMapping("/user")
@Api(value="用户对外接口", description="用户对外接口")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @ApiOperation(value="用户登录",httpMethod="POST",notes="用户登录")
    @PostMapping(value="/userLogin")
    public ResponseUtil userLogin(
            @ApiParam(required=true,name="userLogin",value="POST对象") @RequestBody @Validated UserLogin userLogin
    ){
        try{
            redisService.set("mykey1", "1111");
            for(int i=0; i<100000; i++){
                System.out.println("~");
            }
            String test = redisService.get("mykey1");
            System.out.println(test);
            return ResponseUtil.success(userService.userLogin(userLogin));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtil.fail(ResponseMessage.RETURN_CODE_SERVER_ERROR.getCode(),
                    ResponseMessage.RETURN_CODE_SERVER_ERROR.getMessage());
        }
    }

    @ApiOperation(value="用户查询列表",httpMethod="POST",notes="用户查询列表")
    @PostMapping(value="/listUser")
    public ResponseUtil listUser(
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

    @ApiOperation(value="添加用户",httpMethod="POST",notes="添加用户")
    @PostMapping(value="/insertUser")
    public ResponseUtil insertUser(
            @ApiParam(required=true,name="userInsert",value="POST对象") @RequestBody @Validated UserInsert userInsert,
            Errors errors
    ){
        if(errors.hasErrors()){
            return ResponseUtil.fail(ResponseMessage.RETURN_CODE_PARAM_IS_INVALID.getCode(),
                    ResponseMessage.RETURN_CODE_PARAM_IS_INVALID.getMessage());
        }
        try{
            userService.insertUser(userInsert);
            return ResponseUtil.success(ResponseMessage.SUCCESS);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseUtil.fail(ResponseMessage.RETURN_CODE_SERVER_ERROR.getCode(),
                    ResponseMessage.RETURN_CODE_SERVER_ERROR.getMessage());
        }
    }

    @ApiOperation(value="修改用户",httpMethod="POST",notes="修改用户")
    @PostMapping(value="/updateUser")
    public ResponseUtil updateUser(
            @ApiParam(required=true,name="userInsert",value="POST对象") @RequestBody @Validated UserUpdate userUpdate,
            Errors errors
    ){
        if(errors.hasErrors()){
            return ResponseUtil.fail(ResponseMessage.RETURN_CODE_PARAM_IS_INVALID.getCode(),
                    ResponseMessage.RETURN_CODE_PARAM_IS_INVALID.getMessage());
        }
        try{
            userService.updateUser(userUpdate);
            return ResponseUtil.success(ResponseMessage.SUCCESS);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseUtil.fail(ResponseMessage.RETURN_CODE_SERVER_ERROR.getCode(),
                    ResponseMessage.RETURN_CODE_SERVER_ERROR.getMessage());
        }
    }

    @ApiOperation(value="删除用户",httpMethod="POST",notes="删除用户")
    @PostMapping(value="/deleteUser")
    public ResponseUtil deleteUser(
            @ApiParam(required=true,name="userId",value="用户ID") @RequestParam Long userId
    ){
        try{
            userService.deleteUser(userId);
            return ResponseUtil.success(ResponseMessage.SUCCESS);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseUtil.fail(ResponseMessage.RETURN_CODE_SERVER_ERROR.getCode(),
                    ResponseMessage.RETURN_CODE_SERVER_ERROR.getMessage());
        }
    }

    @ApiOperation(value="批量删除用户",httpMethod="POST",notes="批量删除用户")
    @PostMapping(value="/batchDeleteUser")
    public ResponseUtil batchDeleteUser(
            @ApiParam(required=true,name="userIds",value="用户IDS") @RequestParam String userIds
    ){
        try{
            userService.batchDeleteUser(userIds);
            return ResponseUtil.success(ResponseMessage.SUCCESS);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseUtil.fail(ResponseMessage.RETURN_CODE_SERVER_ERROR.getCode(),
                    ResponseMessage.RETURN_CODE_SERVER_ERROR.getMessage());
        }
    }
}
