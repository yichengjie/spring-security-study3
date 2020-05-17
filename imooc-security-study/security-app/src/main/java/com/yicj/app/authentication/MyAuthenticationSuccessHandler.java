package com.yicj.app.authentication;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yicj.core.properties.SecurityProperties;
import javafx.util.Pair;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


//SavedRequestAwareAuthenticationSuccessHandler为spring security默认的登录成功处理器
@Component("authenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler /*implements AuthenticationSuccessHandler*/ {
    @Autowired
    private ObjectMapper objectMapper ;

    @Autowired
    private SecurityProperties securityProperties ;

    @Autowired
    private ClientDetailsService clientDetailsService ;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices ;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                    HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //获取header中的clientId
        String [] infos = this.obtainUsernameAndPassword(request);
        String clientId = infos[0] ;
        String clientSecret = infos[1] ;
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if (clientDetails ==  null){
            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在：" +clientId) ;
        }else if (!StringUtils.equals(clientDetails.getClientSecret(),clientSecret)){
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配：" + clientId) ;
        }
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP,clientId,clientDetails.getScope(), "custom") ;
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails) ;
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication) ;

        OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

        String content = objectMapper.writeValueAsString(accessToken);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(content);
    }


    /**
     * 获取用户名
     * @param request
     * @return
     * @throws IOException
     */
    private String[] obtainUsernameAndPassword(HttpServletRequest request) throws IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.toLowerCase().startsWith("basic ")) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息") ;
        }
        String[] tokens = extractAndDecodeHeader(header, request);
        assert tokens.length == 2;
        return tokens ;
    }


    /**
     * Decodes the header into a username and password.
     * @throws BadCredentialsException if the Basic header is not present or is not valid
     * Base64
     */
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request)
            throws IOException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        }
        catch (IllegalArgumentException e) {
            throw new BadCredentialsException(
                    "Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");

        int delim = token.indexOf(":");

        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[] { token.substring(0, delim), token.substring(delim + 1) };
    }
}
