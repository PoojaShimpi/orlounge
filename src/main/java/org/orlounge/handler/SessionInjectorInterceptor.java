package org.orlounge.handler;

import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
@WebFilter
@Component
public class SessionInjectorInterceptor extends OncePerRequestFilter {

    public static void setValueToUserTokenLocal(HttpSession session){
        if(session != null && session.getAttribute("USER_TOKEN") != null){
            UserToken userToken = (UserToken) session.getAttribute("USER_TOKEN");
            AppThreadLocal.setTokenLocal(userToken);
        }
    }

    public static void removeUserTokenLocal(){
        AppThreadLocal.setTokenLocal(null);
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session  = ((HttpServletRequest)request).getSession();
        setValueToUserTokenLocal(session);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response) {
        removeUserTokenLocal();
    }



    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        preHandle(httpServletRequest, httpServletResponse);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        postHandle(httpServletRequest, httpServletResponse);
    }
}
