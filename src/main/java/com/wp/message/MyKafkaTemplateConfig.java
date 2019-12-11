package com.wp.message;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ConsumerProperties;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import java.util.Map;

/**
 * @author: wp
 * @Title: MyKafkaTemplate
 * @Description: TODO
 * @date 2019/12/2 14:06
 */
//@Configuration
public class MyKafkaTemplateConfig {
    private static final Logger LOG = LoggerFactory.getLogger(MyKafkaTemplateConfig.class);

    //@Autowired
    ProducerFactory producerFactory;
    //@Autowired
    ProducerListener stringKafkaTemplateProducerListener;
    //@Autowired
    ConsumerFactory<String,String> consumerFactory;

    //@Bean
   // @Primary
    public KafkaTemplate<String,String> stringKafkaTemplate(){
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<String, String>( producerFactory );
        kafkaTemplate.setProducerListener( stringKafkaTemplateProducerListener );
        return kafkaTemplate;
    }

    /*@Bean
    public KafkaTransactionManager transactionManager() {
        KafkaTransactionManager manager = new KafkaTransactionManager(producerFactory);
        return manager;
    }*/

   // @Bean
    public ConcurrentKafkaListenerContainerFactory batchListenerContainerFc(){
        ConcurrentKafkaListenerContainerFactory<String,String> fc = new ConcurrentKafkaListenerContainerFactory<>();
        fc.setConsumerFactory( consumerFactory );
        fc.setBatchListener( true );
        return fc;
    }

    //@Bean
    public ConcurrentKafkaListenerContainerFactory ackListenerContainerFc(){
        ConcurrentKafkaListenerContainerFactory<String,String> fc = new ConcurrentKafkaListenerContainerFactory<>();
        fc.setConsumerFactory( consumerFactory );
        fc.getContainerProperties().setAckMode( ContainerProperties.AckMode.MANUAL_IMMEDIATE );
        fc.setReplyTemplate( stringKafkaTemplate() );
        return fc;
    }

    //@Bean
    public ConcurrentKafkaListenerContainerFactory filterListenerContainerFc(){
        ConcurrentKafkaListenerContainerFactory<String,String> fc = new ConcurrentKafkaListenerContainerFactory<>();
        fc.setConsumerFactory( consumerFactory );
        //设置为true 与记录过滤策略配合使用 被过滤的信息将不会被消费
        fc.setAckDiscarded( true );
        fc.setRecordFilterStrategy( new RecordFilterStrategy<String, String>() {
            @Override
            public boolean filter( ConsumerRecord<String, String> consumerRecord ) {
                //返回true 则代表过滤消息，此消息将被丢弃
                LOG.info( consumerRecord.toString()+" 将被丢弃..." );
                return true;
            }
        } );
        return fc;
    }



}
