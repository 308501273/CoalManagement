package com.coal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailBoxProperties {

    @Value("${coal.mailbox.authCode}")
    private String authCode;
    @Value("${coal.mailbox.emailSender}")
    private String emailSender;
    @Value("${coal.mailbox.effectiveTime}")
    private String effectiveTime;

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public String getAuthCode() {
        return authCode;
    }
}
