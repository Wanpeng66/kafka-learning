package com.wp.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: wp
 * @Title: KafkaConsumer
 * @Description: TODO
 * @date 2019/12/2 16:21
 */
//@Component
public class KafkaConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

    //@KafkaListener(topics = "first",id = "kafka-consumer01",concurrency = "2")
    public void consumer01(String msgData){
        LOG.info("receive data : "+ msgData );
    }

    //@KafkaListener(topics = "first",id = "kafka-consumer-batch",containerFactory = "batchListenerContainerFc",concurrency = "2")
    public void batchConsumer( List<String> msgData){
        LOG.info("receive data : "+ msgData );
    }

    //@KafkaListener(topics = "first",id = "kafka-consumer-ack",concurrency = "1",containerFactory = "ackListenerContainerFc")
    public void ackConsumer( String msgData, Acknowledgment ack ){
        LOG.info("receive data : "+ msgData );

        ack.acknowledge();
    }

    //@KafkaListener(topics = "first",id = "kafka-consumer-reply",concurrency = "1",containerFactory = "ackListenerContainerFc")
    //@SendTo("second")
    public String replyConsumer( String msgData, Acknowledgment ack ){
        LOG.info("receive data : "+ msgData );
        ack.acknowledge();
        return msgData;
    }

    //@KafkaListener(topics = "first",id = "kafka-consumer-filter",containerFactory = "filterListenerContainerFc")
    public void filterConsumer( String msgData ){
        LOG.info("receive data : "+ msgData );
    }

}
