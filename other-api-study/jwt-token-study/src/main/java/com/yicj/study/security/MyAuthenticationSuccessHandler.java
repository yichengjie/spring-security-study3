package com.yicj.study.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yicj.study.model.TokenDetail;
import com.yicj.study.model.TokenDetailImpl;
import com.yicj.study.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: MyAuthenticationSuccessHandler
 * Description: TODO(描述)
 * Date: 2020/5/21 14:27
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper ;
    @Autowired
    private TokenUtils tokenUtils ;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
        HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        TokenDetail tokenDetail = new TokenDetailImpl(username) ;
        String token = tokenUtils.generateToken(tokenDetail);
        response.setContentType("application/json;charset=utf-8");
        Map<String,String> map = new HashMap<>();
        map.put("token",token) ;
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }
}
