package com.yicj.core.vercode;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MyWebAuthenticationDetails extends WebAuthenticationDetails {

    private String imageCode ;
    private String saveImageCode ;

    public MyWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.imageCode = request.getParameter("captcha") ;
        HttpSession session = request.getSession();
        this.saveImageCode = (String)session.getAttribute("captcha") ;
        if (!StringUtils.isEmpty(this.saveImageCode)){
            //随手清除验证码，不管是失败还是成功，所以客户端应在登录失败时刷新验证码
            session.removeAttribute("captcha");
        }
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getSaveImageCode() {
        return saveImageCode;
    }

    public void setSaveImageCode(String saveImageCode) {
        this.saveImageCode = saveImageCode;
    }
}
