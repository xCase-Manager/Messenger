package org.xcasemanager.messenger.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.xcasemanager.messenger.helpers.ObjectUtil;
import org.xcasemanager.messenger.web.rest.resource.ExecutionMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaMessageListener {

    @KafkaListener(topics = "${message.kafka.topic}")
    public void messageListener(final ConsumerRecord<?, ?> consumerRecord) {

        final ExecutionMessageDto message = (ExecutionMessageDto) consumerRecord.value();

        log.info("####################################################################################");
        log.info("");
        log.info("Kafka Listener: " + ObjectUtil.javaObjectToJsonString(message));
        log.info("");
        log.info("####################################################################################");
    }

}