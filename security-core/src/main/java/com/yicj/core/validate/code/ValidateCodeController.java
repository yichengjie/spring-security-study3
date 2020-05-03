package com.yicj.core.validate.code;

import com.yicj.core.properties.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@Slf4j
public class ValidateCodeController {
	//public static final String SESSION_KEY_PREFIX = "SESSION_KEY_PREFIX_";

	@Autowired
	private Map<String,ValidateCodeProcessor> validateCodeProcessorMap ;

	//生成图形验证码
	@GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
			throws Exception {
		validateCodeProcessorMap.get(type +"CodeProcessor").create(new ServletWebRequest(request,response));
	}

}