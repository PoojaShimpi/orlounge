package org.orlounge.handler;

import org.orlounge.bean.LoginBean;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.JwtUtil;
import org.orlounge.common.UserToken;
import org.orlounge.common.util.LoginUtils;
import org.orlounge.factory.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
//@WebFilter
//@Component
//@Order(0)
public class JwtSessionInjectorInterceptor extends OncePerRequestFilter {

    @Autowired
    private LoginUtils loginUtils;

    @Autowired
    ServiceFactory serviceFactory;

    public  void setValueToUserTokenLocal(HttpServletRequest request){
        for(Cookie cookie : request.getCookies()){
            if(cookie.getName().equals("jwt")){
                String jwtString  = cookie.getValue();
                UserToken userToken = JwtUtil.decode(jwtString);

                LoginBean loginBean = serviceFactory.getLoginService().getLoginDAO(userToken.getUserCode());

                UserToken token = loginUtils.prepareToken(loginBean);
                token.setCurrentGroupName(userToken.getCurrentGroupName());
                token.setCurrentGroupId(userToken.getCurrentGroupId());
                token.setCurrentGroupSize(userToken.getCurrentGroupSize());

                Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(token.getRole()));

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        loginBean, null , authorities);

                SecurityContextHolder.setContext(new SecurityContextImpl(auth));

                AppThreadLocal.setTokenLocal(token);
            }
        }

    }

    public static void removeUserTokenLocal(){
        AppThreadLocal.setTokenLocal(null);
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response) {
        setValueToUserTokenLocal(request);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response) {
        AppThreadLocal.clear();
    }



    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        preHandle(httpServletRequest, httpServletResponse);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        postHandle(httpServletRequest, httpServletResponse);
    }
}
