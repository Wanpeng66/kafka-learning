package com.wp.intercepters;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * @author: wp
 * @Title: InterceptorTest
 * @Description: TODO
 * @date 2020/1/14 9:00
 */
public class InterceptorTest {
    public static void main( String[] args ) throws ExecutionException, InterruptedException {
        CountDownLatch latch = new CountDownLatch( 1 );
        ExecutorService threadPool = Executors.newFixedThreadPool( 2,
                new BasicThreadFactory.Builder().namingPattern( "interceptor-thread-pool-%d" ).daemon( true ).build() );

        Future<String> producerSubmit = threadPool.submit( new InterceptorProducerThread( "Interceptor", latch ) );
        Future<String> consumerSubmit = threadPool.submit( new InterceptorConsumerThread( "Interceptor", latch ) );

        System.out.println(producerSubmit.get());
        System.out.println(consumerSubmit.get());

        threadPool.shutdown();
    }
}
