package com.wp.offset;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Map;

/**
 * @author: wp
 * @Title: OffsetConsumerTest
 * @Description: TODO
 * @date 2020/1/16 14:28
 */
public class OffsetConsumerTest {
    public static void main( String[] args ) {
        commitAsync();
    }

    //同步提交的方法
    private static void commitSync() {
        OffsetConsumer offsetConsumer = new OffsetConsumer(  );
        KafkaConsumer consumer = offsetConsumer.getConsumer( "offset" );
        while (true){
            ConsumerRecords records = consumer.poll( Duration.ofSeconds( 1 ) );
            for (Object record : records) {
                System.out.println("......"+record.toString());
            }
            //同步发送offset，会阻塞消费者线程直到broker返回结果
            consumer.commitSync();
            if(!records.isEmpty()){
                break;
            }
        }
        consumer.close();
    }

    //异步提交offset
    private static void commitAsync() {
        OffsetConsumer offsetConsumer = new OffsetConsumer(  );
        KafkaConsumer consumer = offsetConsumer.getConsumer( "offset" );
        while (true){
            ConsumerRecords records = consumer.poll( Duration.ofSeconds( 1 ) );
            for (Object record : records) {
                System.out.println("......"+record.toString());
            }
           consumer.commitAsync( new OffsetCommitCallback() {
               @Override
               public void onComplete( Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception ) {
                    System.out.println(".......异步提交offset回调，offsets:"+offsets.toString());
               }
           } );
            if(!records.isEmpty()){
                break;
            }
        }
        consumer.close();
    }

    private static void bestCommit() {
        OffsetConsumer offsetConsumer = new OffsetConsumer(  );
        KafkaConsumer consumer = offsetConsumer.getConsumer( "offset" );
        //最佳实践：
        //使用异步方式提交偏移量，减少阻塞等待
        //在停止消费者之前，使用同步方式提交偏移量，确保一定提交，然后关闭消费者
        try{
            while (true){
                ConsumerRecords records = consumer.poll( Duration.ofSeconds( 1 ) );
                for (Object record : records) {
                    System.out.println("......"+record.toString());
                }

                consumer.commitAsync( new OffsetCommitCallback() {
                    @Override
                    public void onComplete( Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception ) {
                        System.out.println(".......异步提交offset回调，offsets:"+offsets.toString());
                    }
                } );
                if(!records.isEmpty()){
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                consumer.commitSync();
            }finally{
                consumer.close();
            }
        }


    }
}
