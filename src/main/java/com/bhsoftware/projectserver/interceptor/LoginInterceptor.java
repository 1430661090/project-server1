package com.bhsoftware.projectserver.interceptor;

import com.bhsoftware.projectserver.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String[] requrieAuthPages = {"index",};
        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri, contextPath + "/");
        String page = uri;
        if(begingWith(page, requrieAuthPages)){
            User user = (User)session.getAttribute("user");
            if(user == null){
                response.sendRedirect("login");
                return false;
            }
        }
        return true;
    }


    private boolean begingWith(String page,String[] requrieAuthPages){
        boolean result = false;
        for(String requrieAuthPage : requrieAuthPages){
            if(StringUtils.startsWith(page, requrieAuthPage)){
                result = true;
                break;
            }
        }
        return result;
    }
}
