package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@EnableScheduling
public class ScheduledMessageService {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledMessageService.class);
    private final SimpMessagingTemplate simpMessagingTemplate;

    public ScheduledMessageService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        LOG.error("sendMessage() triggered!");
        final String time = new SimpleDateFormat("HH:mm").format(new Date());
        simpMessagingTemplate.convertAndSend("/topic/messages",
                new OutputMessage("Luke Shartwalker", " is the best!", time));
    }

}