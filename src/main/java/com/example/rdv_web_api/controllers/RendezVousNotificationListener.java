package com.example.rdv_web_api.controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RendezVousNotificationListener {

    @RabbitListener(queues = "notifications-queue")
    public void receiveMessage(String message) {
        System.out.println("Received notification: " + message);
    }
}