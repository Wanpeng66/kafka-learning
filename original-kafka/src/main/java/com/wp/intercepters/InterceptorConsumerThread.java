package com.wp.intercepters;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author: wp
 * @Title: InterceptorConsumerThread
 * @Description: TODO
 * @date 2020/1/14 9:58
 */
@Slf4j
public class InterceptorConsumerThread implements Callable<String> {
    private String topic;
    private CountDownLatch latch;

    public InterceptorConsumerThread( String topic,CountDownLatch latch ) {
        this.topic = topic;
        this.latch = latch;
    }


    @Override
    public String call() throws Exception {
        String result ;
        InterceptorConsumer interceptorConsumer = new InterceptorConsumer( "com.wp.intercepters.ConsumerInterceptor" );
        KafkaConsumer consumer = interceptorConsumer.getConsumer( topic );
        //消费者启动完成后，才允许生产者启动
        latch.countDown();
        while(true){
            ConsumerRecords<String, String> records = consumer.poll( 5000 );
            if(!records.isEmpty()){
                log.info( "拿到消息:"+records );
                result =  records.iterator().next().value();
                break;
            }
        }
        consumer.close();
        return result;
    }
}
