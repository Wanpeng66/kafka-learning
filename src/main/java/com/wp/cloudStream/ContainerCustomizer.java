package com.wp.cloudStream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.stereotype.Component;

/**
 * @author: wp
 * @Title: ContainerCustomizer
 * @Description: TODO
 * @date 2019/12/11 13:36
 */
@Slf4j
public class ContainerCustomizer implements ListenerContainerCustomizer<AbstractMessageListenerContainer> {

    @Override
    public void configure( AbstractMessageListenerContainer container, String destinationName, String group ) {
        log.info( "........................destinationName:"+destinationName+",group:"+group );
        container.getContainerProperties().setAckMode( ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        container.getContainerProperties().setClientId("so57970152");
    }
}
