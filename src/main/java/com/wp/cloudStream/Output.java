package com.wp.cloudStream;

import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * @author: wp
 * @Title: Input
 * @Description: TODO
 * @date 2019/12/4 17:12
 */
public interface Output {
    String OUTPUT = "out";

    String SENDTO = "sendto";

    String SECOND = "second";

    @org.springframework.cloud.stream.annotation.Output(OUTPUT)
    MessageChannel output();

    @org.springframework.cloud.stream.annotation.Output(SENDTO)
    MessageChannel sendto();

    //@org.springframework.cloud.stream.annotation.Output(SECOND)
    MessageChannel second();

}
