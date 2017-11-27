package com.example.demo.producer;

import com.example.demo.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Created by sai.luo on 2017-11-20.
 */
@EnableBinding({ CustomSource.class })
@Component
public class Producer {
    @Autowired
    private CustomSource source;
    private ObjectMapper objectMapper = new ObjectMapper();
    private int count =0 ;
    @Scheduled(fixedRate = 100)
    public void produceHotDrinks() {
        source.output().send(
                MessageBuilder.withPayload("hello hha"+count++).build());
        System.out.println(count);
    }

    @Scheduled(fixedRate = 1000)
    public void produceUsers() throws JsonProcessingException {
        User user = new User();
        user.setId(count);
        user.setName("user"+count);
        user.setAge(18+count);
        source.outputUser().send(
                MessageBuilder.withPayload(objectMapper.writeValueAsString(user)).build());
    }

}
