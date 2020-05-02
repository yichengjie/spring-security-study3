package com.yicj.core.validate.code;

import com.yicj.core.properties.SecurityConstants;
import com.yicj.core.validate.code.image.ImageCode;
import com.yicj.core.validate.code.image.ImageCodeGenerator;
import com.yicj.core.validate.code.sms.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class ValidateCodeController {
	public static final String SESSION_KEY_CODE = "SESSION_KEY_IMAGE_CODE";

	@Autowired
	@Qualifier("imageCodeGenerator")
	private ValidateCodeGenerator imageCodeGenerator ;

	@Autowired
	@Qualifier("smsCodeGenerator")
	private ValidateCodeGenerator smsCodeGenerator ;

	@Autowired
	private SmsCodeSender smsCodeSender ;

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy() ;


	//生成图形验证码
	@GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/image")
	public void createCode(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ServletWebRequest servletWebRequest = new ServletWebRequest(request) ;
		//ImageCode imageCode = imageCodeGenerator.generate(servletWebRequest);
		ImageCode imageCode = (ImageCode)imageCodeGenerator.generate(servletWebRequest);
		sessionStrategy.setAttribute(servletWebRequest, SESSION_KEY_CODE, imageCode);
		ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream()) ;
	}

	//生成短信验证码
	@GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/sms")
	public void createSmsCode(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ServletWebRequest servletWebRequest = new ServletWebRequest(request) ;
		ValidateCode validateCode = smsCodeGenerator.generate(servletWebRequest);
		sessionStrategy.setAttribute(servletWebRequest, SESSION_KEY_CODE, validateCode);
		//获取手机号
		String mobile = ServletRequestUtils.getStringParameter(request, "mobile");
		smsCodeSender.send(mobile, validateCode.getCode());
	}

}