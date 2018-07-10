package com.globot.hmi.attendance.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Ambitous on 2017/12/4.
 */
@Service
public class KafkaServiceImpl implements IKafkaService {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void produce(Object object) {
        for(int i=0;i<2;i++) {
            kafkaTemplate.send("wangjian_kafka_test", "2016110413", String.valueOf(object));
        }
    }
}
