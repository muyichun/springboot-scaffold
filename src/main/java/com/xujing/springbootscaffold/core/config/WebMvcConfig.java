package com.xujing.springbootscaffold.core.config;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {
    //使用阿里 FastJson 作为JSON MessageConverter
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//        FastJsonConfig config = new FastJsonConfig();
//        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);//保留空的字段
//        //SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
//        //SerializerFeature.WriteNullNumberAsZero//Number null -> 0
//        // 按需配置，更多参考FastJson文档哈
//        converter.setFastJsonConfig(config);
//        converter.setDefaultCharset(Charset.forName("UTF-8"));
//        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
//        converters.add(converter);
//    }

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS")
                .maxAge(3600);
    }
    @Bean
    SessionInterceptor getSessionInterceptor() {
        return new SessionInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> whiteList = new ArrayList<>();

        // 从配置文件 resources/whitelist.txt
        InputStream inputStream = this.getClass().getResourceAsStream("/whitelist.txt");
        if (inputStream == null) {
            log.warn("Failed to read whitelist.txt!");
        } else {
            List<String> lines = new ArrayList<>();
            IoUtil.readUtf8Lines(inputStream, lines);
            for (String path : lines) {
                if (!StrUtil.isEmpty(path)) {
                    log.info("exclude path: {}", path);
                    whiteList.add(path);
                }
            }
        }
        registry.addInterceptor(getSessionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(whiteList);
    }

    //添加拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //接口签名认证拦截器，该签名认证比较简单，实际项目中可以使用Json Web Token或其他更好的方式替代。
//        if (!"dev".equals(env)) { //开发环境忽略签名认证
//            registry.addInterceptor(new HandlerInterceptorAdapter() {
//                @Override
//                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//                    //验证签名
//                    boolean pass = validateSign(request);
//                    boolean pass = true;
//                    if (pass) {
//                        return true;
//                    } else {
//                        logger.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
//                                request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
//
//                        Result result = new Result();
//                        result.setCode(ResultCodeEnum.UNAUTHORIZED).setMessage("签名认证失败");
//                        responseResult(response, result);
//                        return false;
//                    }
//                }
//            });
//        }
//    }

//    private void responseResult(HttpServletResponse response, Result result) {
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-type", "application/json;charset=UTF-8");
//        response.setStatus(200);
//        try {
//            response.getWriter().write(JSON.toJSONString(result));
//        } catch (IOException ex) {
//            logger.error(ex.getMessage());
//        }
//    }

//    private String getIpAddress(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        // 如果是多级代理，那么取第一个ip为客户端ip
//        if (ip != null && ip.indexOf(",") != -1) {
//            ip = ip.substring(0, ip.indexOf(",")).trim();
//        }
//        return ip;
//    }
}
