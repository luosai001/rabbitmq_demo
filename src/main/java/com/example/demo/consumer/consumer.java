package com.example.demo.consumer;

import com.example.demo.producer.CustomSource;
import com.example.demo.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sai.luo on 2017-11-20.
 */
@Component
@EnableBinding({ CustomSink.class })
public class consumer {

    private int count = 0 ;
    public static final ExecutorService executorService = Executors.newFixedThreadPool(20);

    private static final Logger log = LoggerFactory.getLogger(consumer.class);
    @Autowired
    private CustomSource source;
    @StreamListener(Sink.INPUT)
    public synchronized <T> void get(GenericMessage<T> msg) throws InterruptedException {
       Thread.sleep(200);
        count ++;
/*        if (count==10){
            log.info("handle error -----> "+msg.getPayload()+" count"+count);
            throw new RuntimeException("handle error -----> "+msg.getPayload()+" count"+count);
        }
        log.info("handel success " + msg.getPayload()+" count"+count);*/
        executorService.submit(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count ++;
            try {
                if (count==10){
                    log.info(" error -----> "+msg.getPayload()+" count"+count);
                    throw new RuntimeException("handle error -----> "+msg.getPayload()+" count: "+count);
                }
                log.info("handel success " + msg.getPayload()+" count"+count);

            }catch (Exception exe){
                log.info("handel error " + msg.getPayload()+" count"+count);

                source.output().send(
                        MessageBuilder.withPayload(msg.getPayload()).build());            }


            System.out.println("handel success " + msg.getPayload()+" count"+count);
        });

    }

}
