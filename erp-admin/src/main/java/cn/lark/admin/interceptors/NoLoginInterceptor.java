package cn.lark.admin.interceptors;

import cn.lark.admin.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器，对于非法请求返回到登录页面
 */
public class NoLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user= (User) request.getSession().getAttribute("user");

        if(null == user){
            /**
             * 用户未登录 或者 session 过期
             */
            response.sendRedirect("index");
            return false;
        }
        return true;
    }
}
