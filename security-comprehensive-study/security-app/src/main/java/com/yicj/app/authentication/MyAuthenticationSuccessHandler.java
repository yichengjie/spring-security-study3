package com.yicj.app.authentication;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yicj.core.properties.LoginType;
import com.yicj.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//SavedRequestAwareAuthenticationSuccessHandler为spring security默认的登录成功处理器
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler /*implements AuthenticationSuccessHandler*/ {
    @Autowired
    private ObjectMapper objectMapper ;

    @Autowired
    private SecurityProperties securityProperties ;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginType loginType = securityProperties.getBrowser().getLoginType();
        if (loginType == LoginType.JOSN){
            String content = objectMapper.writeValueAsString(authentication);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(content);
        }else {
            super.onAuthenticationSuccess(request,response,authentication) ;
        }
    }
}
