package com.wp.intercepters;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.awt.image.RescaleOp;
import java.util.Map;

/**
 * @author: wp
 * @Title: ConsumerInterceptor
 * @Description: TODO
 * @date 2020/1/14 10:23
 */
@Slf4j
public class ConsumerInterceptor  implements org.apache.kafka.clients.consumer.ConsumerInterceptor<String,String> {
    @Override
    public ConsumerRecords<String, String> onConsume( ConsumerRecords<String, String> records ) {
        log.info( records.iterator().next().value()+"开始消费消息......." );
        return records;
    }

    @Override
    public void onCommit( Map<TopicPartition, OffsetAndMetadata> offsets ) {
        log.info( "提交offset......" );
    }

    @Override
    public void close() {

    }

    @Override
    public void configure( Map<String, ?> configs ) {

    }
}
