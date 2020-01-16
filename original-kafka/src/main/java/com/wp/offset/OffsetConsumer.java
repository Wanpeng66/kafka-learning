package com.wp.offset;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author: wp
 * @Title: OffsetConsumer
 * @Description: TODO
 * @date 2020/1/16
 */
public class OffsetConsumer {
    private static Properties props ;

    public OffsetConsumer() {
        setConfig();
    }

    public OffsetConsumer( String clazz) {
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
        props.setProperty( ConsumerConfig.CLIENT_ID_CONFIG,"test-offset-consumer" );
        props.setProperty( ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer" );
        props.setProperty( ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer" );
        props.setProperty( ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false" );
        props.setProperty( ConsumerConfig.GROUP_ID_CONFIG,"offset-consumer-group" );
        props.setProperty( ConsumerConfig.MAX_POLL_RECORDS_CONFIG,"10" );
    }
}
