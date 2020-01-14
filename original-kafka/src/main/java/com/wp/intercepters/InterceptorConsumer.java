package com.wp.intercepters;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author: wp
 * @Title: InterceptorConsumer
 * @Description: TODO
 * @date 2020/1/14 9:51
 */
public class InterceptorConsumer {
    private static Properties props ;

    public InterceptorConsumer() {
        setConfig();
    }

    public InterceptorConsumer(String clazz) {
        setConfig();
        props.setProperty( ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG,clazz );
    }


    public KafkaConsumer getConsumer(String topic){
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>( props );
        consumer.subscribe( Arrays.asList( topic ) );
        return consumer;
    }

    //设置共有参数
    private void setConfig() {
        props = new Properties(  );
        props.setProperty( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"182.61.105.104:9092" );
        props.setProperty( ConsumerConfig.CLIENT_ID_CONFIG,"test-interceptor-consumer" );
        props.setProperty( ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer" );
        props.setProperty( ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer" );
        props.setProperty( ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true" );
        props.setProperty( ConsumerConfig.GROUP_ID_CONFIG,"interceptor-consumer-group" );
        //因为生产者只发送一个消息，所以消费者一次只拉取一个记录
        props.setProperty( ConsumerConfig.MAX_POLL_RECORDS_CONFIG,"1" );
    }
}
