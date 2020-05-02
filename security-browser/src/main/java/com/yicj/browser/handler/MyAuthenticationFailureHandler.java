package com.yicj.browser.handler;

import com.yicj.core.properties.LoginType;
import com.yicj.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//登录失败时的处理逻辑
// SimpleUrlAuthenticationFailureHandler为spring security默认处理器
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler /*implements AuthenticationFailureHandler*/ {

    @Autowired
    private SecurityProperties securityProperties ;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
        if (securityProperties.getBrowser().getLoginType().equals(LoginType.JOSN)){
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            PrintWriter out = response.getWriter();
            //输出失败原因
            //{"error_code":"401","name":"","message":""}
            out.write("{\"error_code\":\"401\",\"name\":\"" + ex.getClass().getName() + "\",\"message\":\"" + ex.getMessage() + "\"}");
        }else {
            super.onAuthenticationFailure(request,response,ex);
        }
    }
}