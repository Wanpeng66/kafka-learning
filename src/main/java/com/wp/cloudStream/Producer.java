package com.wp.cloudStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: wp
 * @Title: Producer
 * @Description:
 * @date 2019/12/4 17:13
 */
@EnableBinding(Output.class)
public class Producer {

    @Autowired
    private Output output;

    public void send() {
        output.output().send( MessageBuilder.withPayload( "cccccccccccc..." ).build() );
    }
}
