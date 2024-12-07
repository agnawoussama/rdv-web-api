package com.example.rdv_web_api.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.notifications}")
    private String exchange; 

    @Value("${rabbitmq.routing-key.notifications}")
    private String routingKey;

    public NotificationController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send")
    public void sendNotification(@RequestBody String message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
