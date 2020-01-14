package com.wp.intercepters;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author: wp
 * @Title: InterceptorProducerThread
 * @Description: TODO
 * @date 2020/1/14 9:31
 */
public class InterceptorProducerThread implements Callable<String> {
    private String topic = "";
    private CountDownLatch latch;

    public InterceptorProducerThread( String topic, CountDownLatch latch ) {
        this.topic = topic;
        this.latch = latch;
    }

    @Override
    public String call() throws Exception {
        //等待消费者启动后才开始发送消息
        latch.await();
        //发送的结果
        final String[] result = {""};
        InterceptorProducer interceptorProducer = new InterceptorProducer( "com.wp.intercepters.IncrCountIntercepter" );
        Producer producer = interceptorProducer.getProducer();
        producer.send( new ProducerRecord( topic, "key", "测试拦截器" ), new Callback() {
            @Override
            public void onCompletion( RecordMetadata metadata, Exception exception ) {
                if(null==exception){
                    result[0] = metadata.toString();
                }else{
                    result[0] = exception.getMessage();
                }
            }
        } );
        producer.close();
        return result[0];
    }
}
