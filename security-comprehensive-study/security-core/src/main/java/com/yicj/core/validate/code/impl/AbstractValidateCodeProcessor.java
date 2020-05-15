package com.yicj.core.validate.code.impl;

import java.util.Map;
import com.yicj.core.validate.code.*;
import com.yicj.core.validate.code.social.HttpSessionSessionStrategy;
import com.yicj.core.validate.code.social.SessionStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;


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

	/**
	 * 根据请求的url获取校验码的类型
	 * @param request
	 * @return
	 */
	private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
		String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
		return ValidateCodeType.valueOf(type.toUpperCase());
	}

	/**
	 * 构建验证码放入session时的key
	 * @param request
	 * @return
	 */
	private String getSessionKey(ServletWebRequest request) {
		return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
	}



	@Override
	public void validate(ServletWebRequest request) {
		ValidateCodeType processorType = getValidateCodeType(request);
		String sessionKey = getSessionKey(request);
		C codeInSession = (C) sessionStrategy.getAttribute(request, sessionKey);
		String codeInRequest;
		try {
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
					processorType.getParamNameOnValidate());
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException("获取验证码的值失败");
		}

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException(processorType + "验证码的值不能为空");
		}

		if (codeInSession == null) {
			throw new ValidateCodeException(processorType + "验证码不存在");
		}

		if (codeInSession.isExpried()) {
			sessionStrategy.removeAttribute(request, sessionKey);
			throw new ValidateCodeException(processorType + "验证码已过期");
		}

		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException(processorType + "验证码不匹配");
		}

		sessionStrategy.removeAttribute(request, sessionKey);
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