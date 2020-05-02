package com.yicj.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        log.info("=======================================");
        log.info("========手机：{}, 验证码：{}=======", mobile, code);
        log.info("=======================================");
    }
}
