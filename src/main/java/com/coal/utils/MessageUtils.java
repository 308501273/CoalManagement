package com.coal.utils;

import com.coal.config.MessageProperties;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class MessageUtils {


    public void sendMessagesCode(String phone, String code, MessageProperties properties) {
        SmsSingleSender ssender = new SmsSingleSender(properties.getAppid(), properties.getAppkey());
        try {
            SmsSingleSenderResult result = ssender.sendWithParam(properties.getNationCode(), phone,
                    properties.getCodeTemplateId() , new ArrayList(Arrays.asList(code,properties.getEffectiveTime())), properties.getSmsSign(), "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
