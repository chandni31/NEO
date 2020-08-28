package com.neo.controllers;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neo.model.User;

@ComponentScan(basePackages="com.neo")
@RestController
@RequestMapping("kafka")
public class ProducerController {

	
    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "Kafka_Example";

    @PostMapping("/publish")
    public String publishMessage(@RequestBody User newUser) {

        kafkaTemplate.send(TOPIC, new User(newUser));
        return "Published successfully";
    }
    
    @GetMapping("/getMessagecount")
    public Long getMessageCount() 
    {
    	
    Properties properties = new Properties();
    properties.put("bootstrap.servers", "127.0.0.1:9092");
    properties.put("group.id", "group_json");
    properties.put("auto.commit.interval.ms", "1000");
    properties.put("auto.offset.reset", "earliest");
    properties.put("session.timeout.ms", "30000");
    properties.put("heartbeat.interval.ms", "3000");
    properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    Long totalCount =(long) 0;
try (final KafkaConsumer<String, User> consumer = new KafkaConsumer<>(properties)) {
    consumer.subscribe(Arrays.asList("Kafka_Example"));
    Set<TopicPartition> assignment;
    while ((assignment = consumer.assignment()).isEmpty()) {
        consumer.poll(Duration.ofMillis(100));
    }
    final Map<TopicPartition, Long> endOffsets = consumer.endOffsets(assignment);
    final Map<TopicPartition, Long> beginningOffsets = consumer.beginningOffsets(assignment);
    assert (endOffsets.size() == beginningOffsets.size());
    assert (endOffsets.keySet().equals(beginningOffsets.keySet()));

     totalCount = beginningOffsets.entrySet().stream().mapToLong(entry -> {
            TopicPartition tp = entry.getKey();
            Long beginningOffset = entry.getValue();
            Long endOffset = endOffsets.get(tp);
            return endOffset - beginningOffset;
        }).sum();
}  
return totalCount;
    }   
}
