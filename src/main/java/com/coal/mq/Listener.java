package com.coal.mq;

import com.coal.config.MailBoxProperties;
import com.coal.config.MessageProperties;
import com.coal.utils.MailBoxUtils;
import com.coal.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Slf4j
@Component
public class Listener {
    @Autowired
    private MessageUtils messageUtils;
    @Autowired
    private MailBoxUtils mailBoxUtils;
    @Autowired
    private MessageProperties messageProperties;
    @Autowired
    private MailBoxProperties mailBoxProperties;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "coal.sms.code",durable = "true"),
            exchange = @Exchange(name = "coal.sms.exchange", type = ExchangeTypes.TOPIC),
            key = {"coal.sms.code"}
    ))
    public void listenToSendMessageCode(Map<String,String> msg){

        if(CollectionUtils.isEmpty(msg)){
            return;
        }
        String phone = msg.remove("phone");
        String code=msg.remove("code");
        if(StringUtils.isBlank(code)|| StringUtils.isBlank(phone)){
            return;
        }
        messageUtils.sendMessagesCode(phone,code,messageProperties);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "o2o.mailbox.code",durable = "true"),
            exchange = @Exchange(name = "o2o.mailbox.exchange", type = ExchangeTypes.TOPIC),
            key = {"o2o.mailbox.code"}
    ))

    public void listenToSendMailCode(Map<String,String> msg){

        if(CollectionUtils.isEmpty(msg)){
            return;
        }
        String email = msg.remove("email");
        String code=msg.remove("code");
        if(StringUtils.isBlank(code)|| StringUtils.isBlank(email)){
            return;
        }
        mailBoxUtils.sendCode(email,code,mailBoxProperties);

    }


}