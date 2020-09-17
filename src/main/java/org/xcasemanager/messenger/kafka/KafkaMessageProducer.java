package org.xcasemanager.messenger.kafka;

import lombok.extern.slf4j.Slf4j;
import org.xcasemanager.messenger.helpers.ObjectUtil;
import org.xcasemanager.messenger.web.rest.resource.ExecutionMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class KafkaMessageProducer {

    @Autowired
    private KafkaTemplate<String, ExecutionMessageDto> messageKafkaTemplate;

    @Value(value = "${message.kafka.topic}")
    private String messageTopicName;
    private static final Logger log = LoggerFactory.getLogger(ExecutionMessageDto.class);

    public void sendMessage(final ExecutionMessageDto message) {

        ListenableFuture<SendResult<String, ExecutionMessageDto>> future = messageKafkaTemplate.send(messageTopicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String,ExecutionMessageDto>>() {

            @Override
            public void onSuccess(SendResult<String,ExecutionMessageDto> result) {
                log.info("####################################################################################");
                log.info("");
                log.info("Kafka Producer: " + ObjectUtil.javaObjectToJsonString(result.getRecordMetadata()));
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });

    }

}
