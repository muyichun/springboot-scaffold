package com.xujing.springbootscaffold.modules.common;

import com.alibaba.fastjson.JSONObject;
import com.xujing.springbootscaffold.core.api.ApiController;
import com.xujing.springbootscaffold.core.api.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/*
 *  登陆通用方法
 * @Author muyichun
 * @Date 2022-03-02 14:58:21$
 */
@RestController
@Slf4j
@Api(tags = "登录相关接口-公共类")
public class LoginController extends ApiController {
    @GetMapping("/login")
    @ApiOperation("使用用户名和密码登陆")
    public Result<JSONObject> loginByMobile(@RequestParam String username,
                                            @RequestParam String pwd,
                                            HttpSession session) {
        //1. 登陆验证
        //2. 成功则生成token，并返回页面权限列表  失败则报错
//        User user = new User();
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("token", TokenUtil.createToken(String.valueOf(user.getId()), TokenUtil.SYSTEM_USER));
        return success(jsonObject);
    }
}