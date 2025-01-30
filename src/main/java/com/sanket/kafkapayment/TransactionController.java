package com.sanket.kafkapayment;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionRepository repository;
    private final KafkaProducer producer;

    public TransactionController(KafkaProducer producer, TransactionRepository repository) {
        this.producer = producer;
        this.repository = repository;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody TransactionRequest request) throws JsonProcessingException {
        Transaction tx =  new Transaction();
        tx.setAmount(request.amount());
        tx.setCurrency(request.currency());
        tx.setSenderId(request.senderId());
        tx.setReceiverId(request.receiverId());
        tx.setStatus("PENDING");
        tx.setCreatedAt(LocalDateTime.now());

        tx =  repository.save(tx);
        producer.sendTransaction(tx);
        return tx;
    }

    public record TransactionRequest(
            BigDecimal amount,
            String currency,
            String senderId,
            String receiverId
    ) {}
}
