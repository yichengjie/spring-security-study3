package com.yicj.core.validate.code;

import com.yicj.core.properties.SecurityConstants;
import com.yicj.core.validate.code.image.ImageCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler ;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy() ;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //如果是登录请求，做验证码的校验
        if (StringUtils.equals(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,request.getRequestURI())
                && StringUtils.equalsAnyIgnoreCase(request.getMethod(),"POST")){
            try {
                validate(new ServletWebRequest(request)) ;
            }catch (ValidateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        //如果验证码通过校验
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request,ValidateCodeController.SESSION_KEY_IMAGE_CODE) ;
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"imageCode") ;

        if (StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证码的值不能为空") ;
        }
        if (codeInSession == null){
            throw new ValidateCodeException("验证码不存在") ;
        }
        if (codeInSession.isExpried()){
            sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY_IMAGE_CODE);
            throw new ValidateCodeException("验证码已过期") ;
        }

        if(!StringUtils.equals(codeInRequest,codeInSession.getCode())){
            throw new ValidateCodeException("验证码不匹配") ;
        }
        sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY_IMAGE_CODE);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
