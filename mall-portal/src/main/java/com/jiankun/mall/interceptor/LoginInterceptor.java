package com.jiankun.mall.interceptor;

import com.jiankun.mall.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/3 17:31
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.判断用户有没有登录
        //2.如果登录了，就放行，可以访问后台的资源
        //3.如果没有登录，跳转到登录界面
        System.out.println("Request URI: " + request.getRequestURI());
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            //没有登录，跳转到登录界面
            response.sendRedirect("/page/login?requestURI=" + requestURI);
            return false;
        }

        return true;
    }
}
