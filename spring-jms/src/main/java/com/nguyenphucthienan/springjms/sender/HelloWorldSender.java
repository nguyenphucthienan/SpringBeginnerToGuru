package com.nguyenphucthienan.springjms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyenphucthienan.springjms.config.JmsConfig;
import com.nguyenphucthienan.springjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class HelloWorldSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello World")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.HELLO_WORLD_QUEUE, message);
    }

    @Scheduled(fixedRate = 2000)
    public void sendAndReceivedMessage() throws JMSException {
        HelloWorldMessage helloWorldMessage = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello")
                .build();

        Message receivedMessage = jmsTemplate.sendAndReceive(JmsConfig.SEND_RECEIVE_QUEUE, session -> {
            Message message;
            try {
                message = session.createTextMessage(objectMapper.writeValueAsString(helloWorldMessage));
                message.setStringProperty("_type", "com.nguyenphucthienan.springjms.model.HelloWorldMessage");
            } catch (JsonProcessingException e) {
                throw new JMSException(e.getMessage());
            }
            return Objects.requireNonNull(message);
        });

        log.info("Received Message: {}", Objects.requireNonNull(receivedMessage).getBody(String.class));
    }
}
