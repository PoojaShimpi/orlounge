package org.orlounge.config;

import org.orlounge.business.UserServiceImpl;
import org.orlounge.handler.MyPermissionEvaluator;
import org.orlounge.handler.SuccessHandler;
import org.orlounge.service.OrLoungePasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    SuccessHandler successHandler;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    OrLoungePasswordEncoder orLoungePasswordEncoder;
    @Autowired
    private MyPermissionEvaluator permissionEvaluator;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "assets/vendor/codemirror/mode/scheme",
                        "/static/**",
                        "/js/**",
                        "/others/**",
                        "/images/**"
                ).anonymous()
                .antMatchers(
                        "/",
                        "/index.html" ,
                        "/register-holder.html",
                        "/login.html" ,
                        "/invite.html" ,
                        "/inviteUser",
                        "/j_spring_security_check",
                        "/registerUser",
                        "/registerUser" ,
                        "/checkUserNameExists",
                        "/**.html",
                        "/**"
                ).permitAll()
                .antMatchers(
                        "/home.html",
                        "/manageUsers.html",
                        "/procedure-manuals.html",
                        "/staffinfolist.html" ,
                        "/call-schedule.html" ,
                        "/notices.html" ,
                        "/inservice.html",
                         "/postop.html",
                        "/checklists.html" ,
                        "/messageboard.html"
                ).authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/j_spring_security_check")
                .successHandler(successHandler)
                .failureUrl("/index.html")
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .deleteCookies("jwt")
                .invalidateHttpSession(Boolean.TRUE)
                .and()
                .userDetailsService(userService);


    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(permissionEvaluator);
        web.expressionHandler(handler);
    }


}
