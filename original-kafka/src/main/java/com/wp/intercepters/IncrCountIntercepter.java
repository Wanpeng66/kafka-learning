package com.wp.intercepters;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author: wp
 * @Title: IncrCountIntercepter
 * @Description: 生产者发送消息拦截  计数加一
 * @date 2020/1/13 17:15
 */
@Slf4j
public class IncrCountIntercepter implements ProducerInterceptor {
    /**
    * @Author wp
    * @Description 发送之前调用
    * @Date  2020/1/13 17:17
    * @Param  * @param record
    * @return org.apache.kafka.clients.producer.ProducerRecord
    */
    @Override
    public ProducerRecord onSend( ProducerRecord record ) {
        log.info( record.toString()+"开始发送..." );
        return null;
    }

    /**
    * @Author wp
    * @Description 发送消息得到ack之后调用
    * @Date  2020/1/13 17:18
    * @Param  * @param metadata
     * @param exception
    * @return void
    */
    @Override
    public void onAcknowledgement( RecordMetadata metadata, Exception exception ) {
        log.info( metadata.toString() );
    }

    @Override
    public void close() {

    }

    @Override
    public void configure( Map<String, ?> configs ) {

    }
}
