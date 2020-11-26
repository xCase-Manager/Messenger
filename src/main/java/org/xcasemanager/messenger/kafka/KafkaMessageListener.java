package org.xcasemanager.messenger.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.xcasemanager.messenger.web.rest.resource.ExecutionMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaMessageListener {

    private static final Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "${message.kafka.topic}")
    public void messageListener(final ConsumerRecord<String, ExecutionMessageDto> record) {
        log.info("- Message: \n" +
            "offset = " + record.offset() + "\n" + 
            "key = " + record.key() + "\n" + 
            "value = " + record.value() + "\n");
    }
}