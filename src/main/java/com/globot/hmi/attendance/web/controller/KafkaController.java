package com.globot.hmi.attendance.web.controller;

import com.globot.hmi.attendance.service.IKafkaService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ambitous on 2017/12/4.
 */
@Controller
@RequestMapping("/api/kafka")
public class KafkaController {

    @Autowired
    IKafkaService kafkaService;

    @RequestMapping("/produce")
    public void produce(){

        kafkaService.produce("hello wjjjjj");
    }
}
