package com.wp.cloudStream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: wp
 * @Title: ProducerTest
 * @Description: TODO
 * @date 2019/12/4 17:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProducerTest {

    @Autowired
    Producer producer;

    @Test
    public void send(){
        log.info( ".......开始发送消息........" );
        producer.send();
        log.info( ".......消息发送结束......." );
    }

}