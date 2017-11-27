package com.example.demo.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by sai.luo on 2017-11-20.
 */
public interface CustomSink {
    String INPUTUSER = "inputUser" ;
    @Input(Sink.INPUT)
    SubscribableChannel input();
    @Input(INPUTUSER)
    SubscribableChannel inputUser();
}
