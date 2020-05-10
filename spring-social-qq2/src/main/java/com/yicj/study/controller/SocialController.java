package com.yicj.study.controller;

import com.yicj.study.config.SocialConfig;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class SocialController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils ;

    private RequestCache requestCache = new HttpSessionRequestCache() ;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy() ;


    @GetMapping(SocialConfig.REGISTER_URI)
    public String socialRegister(){
        System.out.println("hello world");
        return "sigup" ;
    }


    /**
     * 首次使用qq登录的时候会跳到注册页面
     * @param request
     * @return
     */
    @PostMapping(SocialConfig.REGISTER_URI)
    public String socialRegister(ServletWebRequest  request){
        //已经使用静默登录后这里不需要再次保存
        // 通过request对象获取Connect
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        // 执行Connection持久化
        providerSignInUtils.doPostSignUp(connection.getKey().getProviderUserId(), request);
        //执行绑定账号，信息完善等其他逻辑
        //....
        // 跳转页面
        return "redirect:/home.html" ;
    }


    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, HttpServletResponse response){
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是: {}",targetUrl);
        }
        return "login" ;
    }

}
