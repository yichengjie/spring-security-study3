package com.yicj.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QQUserInfo implements OAuth2User {

    //统一赋予user角色
    private List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER") ;
    private Map<String,Object> attributes ;

    @Getter
    @Setter
    private String nickname ;
    //
    @Getter
    @Setter
    @JsonProperty("figureurl")
    private String figureUrl30 ;
    //
    @Getter
    @Setter
    @JsonProperty("figureurl_1")
    private String figureUrl50 ;
    //
    @Getter
    @Setter
    @JsonProperty("figureurl_2")
    private String figureUrl100 ;
    //
    @Getter
    @Setter
    @JsonProperty("figureurl_qq_1")
    private String qqFigureUrl40 ;
    //
    @Getter
    @Setter
    @JsonProperty("figureurl_qq_2")
    private String qqFigureUrl100 ;
    //
    @Getter
    @Setter
    private String gender ;
    // 携带openId备用
    @Getter
    @Setter
    private String openId ;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (this.attributes == null){
            this.attributes = new HashMap<>() ;
            this.attributes.put("nickname",this.getNickname()) ;
            this.attributes.put("figureUrl30",this.getFigureUrl30()) ;
            this.attributes.put("figureUrl50",this.getFigureUrl50()) ;
            this.attributes.put("figureUrl100",this.getFigureUrl100()) ;
            this.attributes.put("qqFigureUrl40",this.getQqFigureUrl40()) ;
            this.attributes.put("qqFigureUrl100",this.getQqFigureUrl100()) ;
            this.attributes.put("gender", this.getGender()) ;
            this.attributes.put("openId", this.getOpenId()) ;
        }


        return null;
    }

    @Override
    public String getName() {
        return nickname;
    }
}
