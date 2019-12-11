package com.wp.cloudStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author: wp
 * @Title: Consumer
 * @Description: TODO
 * @date 2019/12/5 13:22
 */
@EnableBinding({Input.class,Output.class})
public class Consumer {

    private static final Logger LOG = LoggerFactory.getLogger(Consumer.class);

    @StreamListener(Input.INPUT)
    //@SendTo(Output.SENDTO)
    public void listen( Message<String> data){
        LOG.info( "recieve data: "+data+"......................" );
        Acknowledgment acknowledgment = data.getHeaders().get( KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        if (acknowledgment != null) {
            System.out.println("Acknowledgment provided");
            acknowledgment.acknowledge();
        }
        //return "send to......";
    }
}
