package org.orlounge.auth;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.orlounge.common.AppThreadLocal;
import org.springframework.stereotype.Component;

import javax.security.auth.message.AuthException;

@Aspect
@Component
public class AuthCheckAspect {

    @Pointcut("execution(* (@org.orlounge.auth.AuthCheck *).*(..))")
    public void allMethods() {}

    @Pointcut("execution(@org.orlounge.auth.AuthCheck * *(..))")
    public void allMethodsWithAuthCheck(){}


    @Pointcut("allMethods() || allMethodsWithAuthCheck()")
    public void authorizeMethod() {}


    @Before("authorizeMethod()")
    public void checkAuth() throws AuthException {
        if(AppThreadLocal.getTokenLocal() == null){
            throw new AuthException("Invalid Access ! Re - login");
        }
    }
}
