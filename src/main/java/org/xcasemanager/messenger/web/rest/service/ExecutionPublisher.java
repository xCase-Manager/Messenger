package org.xcasemanager.messenger.web.rest.impl;

import org.xcasemanager.messenger.kafka.KafkaMessageProducer;
import org.xcasemanager.messenger.service.MessageService;
import org.xcasemanager.messenger.web.rest.resource.ExecutionMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExecutionPublisher implements MessageService {

    @Autowired
    private KafkaMessageProducer kafkaMessageProducer;

    @Override
    public void addMessage(final ExecutionMessageDto message) {
        kafkaMessageProducer.sendMessage(message);
    }
}