package com.sanket.kafkapayment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final TransactionRepository repository;
    private final ObjectMapper objectMapper; // Injected configured mapper

    @KafkaListener(topics = "${app.kafka-topic}")
    public void processTransaction(String message) {
        try {
            Transaction tx = objectMapper.readValue(message, Transaction.class);
            tx.setStatus("PROCESSED");
            repository.save(tx);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize transaction", e);
        }
    }
}