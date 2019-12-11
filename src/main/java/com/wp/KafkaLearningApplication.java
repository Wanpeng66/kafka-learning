package com.wp;

import com.wp.cloudStream.Output;
import com.wp.cloudStream.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableKafka
@RestController
public class KafkaLearningApplication {

    @Autowired
    Producer producer;

    public static void main( String[] args ) {
        SpringApplication.run( KafkaLearningApplication.class, args );
    }

    @GetMapping("/send")
    public String send( ){
        producer.send();
        return "success";
    }


}
