package com.xujing.springbootscaffold.core.config;

import com.alibaba.fastjson.JSONObject;
import com.xujing.springbootscaffold.core.common.TokenUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


@Slf4j
public class SessionInterceptor implements HandlerInterceptor {

    @Value("${spring.profiles.active:dev}")
    private String env;

    public static final String CURRENT_USER = "CURRENT_USER";

    @SneakyThrows
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String method = request.getMethod();
        //测试环境不需要token
        if ("OPTIONS".equals(method) || "dev".equals(env)) {
            return true;
        }
        //认证
        String token = request.getHeader("token");
        String uri = request.getRequestURI();
        TokenUtil.TokenParsedResult result = TokenUtil.parseTokenStr(token);
        if (result.getParseResult() == 0) {
            //token校验成功后取出token绑定的登录用户
            String currentUserId = result.getId();
//            CurrentUser currentUser = memberService.getUserInfo(currentUserId);
//            request.setAttribute(CURRENT_USER,currentUser);
            return true;
        }
        // 记录需要拦截，但是没有通过的请求路径
        log.warn("【拦截器】需要拦截的路径，但没有通过的请求路径：{}", uri);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject res = new JSONObject();
        res.put("data", "false");
        res.put("code", "403");
        res.put("message", "访问无权限-拦截");
        PrintWriter out = response.getWriter();
        out.append(res.toString());
        return false;
    }
}