package com.wp.intercepters;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wp
 * @Title: InterceptorProducer
 * @Description: TODO
 * @date 2020/1/14 9:12
 */
public class InterceptorProducer {
    private static Properties props ;
    //private static KafkaProducer<String,String> producer;
    //private static ReentrantLock lock = new ReentrantLock();


    public InterceptorProducer(){
        setConfig();
    }

    public InterceptorProducer(String clazz){
        setConfig();
        //设置拦截器
        props.setProperty( ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,clazz );
    }

    public Producer getProducer(){
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>( props );
        return producer;
    }



    //设置共有参数
    private void setConfig() {
        props = new Properties(  );
        props.setProperty( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"182.61.105.104:9092" );
        props.setProperty( ProducerConfig.ACKS_CONFIG,"1" );
        props.setProperty( ProducerConfig.CLIENT_ID_CONFIG,"test-interceptor-producer" );
        props.setProperty( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer" );
        props.setProperty( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer" );
        props.setProperty( ProducerConfig.RETRIES_CONFIG,"3" );
    }




}
