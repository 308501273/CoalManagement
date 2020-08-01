package com.coal.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MessageProperties {

    @Value("${coal.sms.appid}")
    private int appid;
    @Value("${coal.sms.appkey}")
    private String appkey;
    @Value("${coal.sms.smsSign}")
    private String smsSign;
    @Value("${coal.sms.nationCode}")
    private String nationCode;
    @Value("${coal.sms.codeTemplateId}")
    private int codeTemplateId;
    @Value("${coal.sms.effectiveTime}")
    private String effectiveTime;

}
