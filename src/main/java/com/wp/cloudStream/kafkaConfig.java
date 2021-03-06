package com.wp.cloudStream;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.stream.binder.kafka.KafkaMessageChannelBinder;
import org.springframework.cloud.stream.binder.kafka.properties.KafkaConsumerProperties;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.cloud.stream.config.MessageSourceCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.*;

/**
 * @author: wp
 * @Title: kafkaConfig
 * @Description: TODO
 * @date 2019/12/9 15:23
 */
@Configuration
@Slf4j
public class kafkaConfig {
    @Autowired
    Producer producer;

    //针对不能在配置文件中配置的消费者参数 可以创建定制器去配置
    @Bean
    public ListenerContainerCustomizer<AbstractMessageListenerContainer<Object, Object>> customizer() {
        return (container, dest, group) -> {
            container.getContainerProperties().setAckMode( ContainerProperties.AckMode.MANUAL_IMMEDIATE);
            container.getContainerProperties().setClientId("so57970152");
        };
    }
    @Bean
    public ApplicationRunner runner( ) {
        return args -> producer.send();
    }


    }

