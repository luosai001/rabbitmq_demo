package com.example.demo.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by sai.luo on 2017-11-20.
 */
public interface CustomSource {
    @Output
    MessageChannel output();
    @Output
    MessageChannel outputUser();
}
