package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        // 1. 获取请求URL
        String url = req.getRequestURL().toString();
        log.info("获取的url：{}",url);

        // 2. 判断请求url中是否包含login，如是，说明是登陆操作，直接放行
        if (url.contains("login")){
            log.info("是登陆操作，直接放行...");
            return true;
        }
        // 3. 获取请求头中的token
        String jwt = req.getHeader("token");

        // 4. 判断令牌是否存在，如否，返回错误结果（未登录）
        if (!StringUtils.hasLength(jwt)){
            log.info("请求头中没有token，未登录！");
            Result error = Result.error("NOT_LOGIN");
            // 手动将error对象转成json
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }

        // 5. 解析token，如失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) { // 令牌解析失败
            e.printStackTrace();
            log.info("令牌解析失败，返回错误信息");
            Result error = Result.error("NOT_LOGIN");
            // 手动将error对象转成json
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }
        // 6. 放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle ...");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion ...");

    }
}
