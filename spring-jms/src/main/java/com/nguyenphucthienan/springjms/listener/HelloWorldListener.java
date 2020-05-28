package com.nguyenphucthienan.springjms.listener;

import com.nguyenphucthienan.springjms.config.JmsConfig;
import com.nguyenphucthienan.springjms.model.HelloWorldMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Slf4j
@Component
public class HelloWorldListener {

    @JmsListener(destination = JmsConfig.HELLO_WORLD_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message) {

        log.info("Message: {}", helloWorldMessage);
    }
}
