package com.bank.msyanki.kafka;

import com.bank.msyanki.entity.YankiAccount;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class YankiConsumer {
    @KafkaListener(topics = "yanki-transactions", groupId = "yanki-group")
    public void consumeTransaction(YankiAccount transaction) {
        System.out.println("Transacción recibida: " + transaction);
    }
}
