package com.yicj.core.validate.code.impl;

import java.util.Map;

import com.yicj.core.validate.code.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author zhailiang
 *
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

	//操作session的工具类
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	//收集系统中所有的 {@link } 接口的实现。
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;


	@Override
	public void create(ServletWebRequest request) throws Exception {
		C validateCode = generate(request);
		save(request, validateCode);
		send(request, validateCode);
	}

	//成校验码
	private C generate(ServletWebRequest request) {
		String type = getProcessorType(request) ;
		String generatorName = type + "CodeGenerator";
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
		if (validateCodeGenerator == null) {
			throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
		}
		return (C) validateCodeGenerator.generate(request);
	}


	private String getProcessorType(ServletWebRequest request){
		return StringUtils.substringAfter(request.getRequest().getRequestURI(),"/code/") ;
	}

	//保存校验码
	private void save(ServletWebRequest request, C validateCode) {
		String key = SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase() ;
		sessionStrategy.setAttribute(request, key , validateCode);
	}


	//发送校验码，由子类实现
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

}