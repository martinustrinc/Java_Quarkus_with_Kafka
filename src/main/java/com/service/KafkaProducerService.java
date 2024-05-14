package com.service;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class KafkaProducerService {

    private static final Logger LOG = Logger.getLogger(KafkaProducerService.class);

    @Inject
    @Channel("kafka-producer")
    Emitter<String> kafkaProducer;

    @ConfigProperty(name = "quarkus.kafka.topic")
    String kafkaTopic;

    public void publishToKafka(String data) {
        try {
            LOG.info("Publishing data to Kafka topic: " + kafkaTopic);
            kafkaProducer.send(String.valueOf(new ProducerRecord<>(kafkaTopic, data)));
            LOG.info("Data published successfully to Kafka topic: " + kafkaTopic);
        } catch (Exception e) {
            LOG.error("Failed to publish data to Kafka", e);
        }
    }
}
