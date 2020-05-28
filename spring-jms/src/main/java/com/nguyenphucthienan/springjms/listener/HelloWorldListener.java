package com.nguyenphucthienan.springjms.listener;

import com.nguyenphucthienan.springjms.config.JmsConfig;
import com.nguyenphucthienan.springjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class HelloWorldListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.HELLO_WORLD_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message) {

        log.info("Message: {}", helloWorldMessage);
    }

    @JmsListener(destination = JmsConfig.SEND_RECEIVE_QUEUE)
    public void listenForMessage(@Payload HelloWorldMessage helloWorldMessage,
                                 @Headers MessageHeaders headers, Message message) throws JMSException {

        HelloWorldMessage payloadMessage = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("World")
                .build();

        log.info("Message: {}", helloWorldMessage);
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMessage);
    }
}
