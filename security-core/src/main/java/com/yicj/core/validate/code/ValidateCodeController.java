package com.yicj.core.validate.code;

import com.yicj.core.properties.SecurityConstants;
import com.yicj.core.validate.code.image.ImageCode;
import com.yicj.core.validate.code.image.ImageCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ValidateCodeController {
	public static final String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

	@Autowired
	private ImageCodeGenerator imageCodeGenerator ;

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy() ;

	/**
	 * 创建验证码，根据验证码类型不同，调用不同的接口实现
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/image")
	public void createCode(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ServletWebRequest servletWebRequest = new ServletWebRequest(request) ;
		ImageCode imageCode = imageCodeGenerator.generate(servletWebRequest);
		sessionStrategy.setAttribute(servletWebRequest,SESSION_KEY_IMAGE_CODE,imageCode);
		ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream()) ;
	}

}