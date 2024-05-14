package com.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Duration;
import java.util.Collections;

@ApplicationScoped
public class KafkaConsumerService {

    private static final Logger LOG = Logger.getLogger(KafkaConsumerService.class);

    @Inject
    KafkaConsumer kafkaConsumer;

    public void startConsuming() {
        kafkaConsumer.subscribe(Collections.singleton("my-topic"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                // Process the received data
                // Example: Persist the data to a database
                // Your implementation here

                LOG.infof("Received message: key=%s, value=%s", record.key(), record.value());
            }
        }
    }
}
