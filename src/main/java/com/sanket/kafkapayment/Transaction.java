package com.sanket.kafkapayment;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "transaction_id", columnDefinition = "UUID")
    private UUID id;
    private BigDecimal amount;
    private String currency;
    private String senderId;
    private String receiverId;
    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Transaction(UUID id, BigDecimal amount, String currency, String senderId, String receiverId, String status, String createdAt) {
        this.amount = amount;
        this.currency = currency;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
        this.createdAt = LocalDateTime.parse(createdAt);
    }
}
