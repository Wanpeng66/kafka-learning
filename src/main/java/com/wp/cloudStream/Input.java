package com.wp.cloudStream;

import org.springframework.messaging.SubscribableChannel;

/**
 * @author: wp
 * @Title: Input
 * @Description: TODO
 * @date 2019/12/5 13:20
 */

public interface Input {

    String INPUT = "in";

    @org.springframework.cloud.stream.annotation.Input(INPUT)
    SubscribableChannel input();
}
