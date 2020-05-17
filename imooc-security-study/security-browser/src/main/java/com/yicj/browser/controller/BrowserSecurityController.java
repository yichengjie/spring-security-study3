package com.yicj.browser.controller;

import com.yicj.core.model.SimpleResponse;
import com.yicj.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class BrowserSecurityController {


    @Autowired
    private SecurityProperties securityProperties ;

    private RequestCache requestCache = new HttpSessionRequestCache() ;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy() ;

    /**
     * 当需要身份认证时，跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是: {}",targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage()) ;
            }
        }
        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页面");
    }

    @GetMapping("/hello")
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public SimpleResponse hello(){
        return new SimpleResponse("hello world") ;
    }
}
