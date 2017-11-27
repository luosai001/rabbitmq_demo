package com.example.demo.consumer;

import com.example.demo.model.User;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * Created by sai.luo on 2017-11-20.
 */
@EnableBinding(CustomSink.class)
public class UserHandle {
    @StreamListener(CustomSink.INPUTUSER)
    public void handle(User user){
        System.out.println("处理接收的消息"+user.toString());
    }
}
