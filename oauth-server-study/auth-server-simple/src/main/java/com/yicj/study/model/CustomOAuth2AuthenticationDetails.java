package com.yicj.study.model;

import lombok.*;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * CustomOAuth2AuthenticationDetails
 * </p>
 *
 * @author yicj
 * @since 2024/08/28 14:10
 */
@Getter
@Setter
public class CustomOAuth2AuthenticationDetails extends OAuth2AuthenticationDetails {

    private String address ;

    private String author ;

    private String organization ;

    public CustomOAuth2AuthenticationDetails(HttpServletRequest request) {
        super(request);
    }
}
