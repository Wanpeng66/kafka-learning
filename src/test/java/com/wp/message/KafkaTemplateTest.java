package com.wp.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author: wp
 * @Title: KafkaTemplateTest
 * @Description: TODO
 * @date 2019/12/2 14:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTemplateTest  {

    @Autowired
    KafkaTemplate stringKafkaTemplate;


    @Test
    //@Transactional
    public void test01() throws Exception {
        for(int i=0;i<3;i++){
            stringKafkaTemplate.send( "first",i+"with tx..." );
        }

        //stringKafkaTemplate.send( "second","with tx..." );
        //TimeUnit.SECONDS.sleep( 2 );
    }
}
