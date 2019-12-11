package com.wp.message;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 * @author: wp
 * @Title: StringKafkaTemplateProducerListener
 * @Description: TODO
 * @date 2019/12/2 14:54
 */
//@Component
public class StringKafkaTemplateProducerListener implements ProducerListener<String, String> {

    private static final Logger log = LoggerFactory.getLogger(StringKafkaTemplateProducerListener.class);

    @Override
    public void onSuccess( ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        log.info("Message send success : " + producerRecord.toString());
    }

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {
        log.info("Message send error : " +exception +" , producerRecord  :" + producerRecord.toString());
    }
}
