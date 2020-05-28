package com.nguyenphucthienan.springjms.sender;

import com.nguyenphucthienan.springjms.config.JmsConfig;
import com.nguyenphucthienan.springjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloWorldSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello World")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.HELLO_WORLD_QUEUE, message);
    }
}
