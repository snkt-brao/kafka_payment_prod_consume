package com.sanket.kafkapayment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;  // Injected configured mapper

        @Value("${app.kafka-topic}")
        private String topic;


    public void sendTransaction(Transaction transaction) {
        try {
            String message = objectMapper.writeValueAsString(transaction);
            kafkaTemplate.send(topic, transaction.getId().toString(), message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize transaction", e);
        }
    }
}