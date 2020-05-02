package com.yicj.core.validate.code;

import com.yicj.core.validate.code.image.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {

	ImageCode generate(ServletWebRequest request);
	
}
