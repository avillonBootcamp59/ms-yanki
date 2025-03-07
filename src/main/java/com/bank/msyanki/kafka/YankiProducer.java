package com.bank.msyanki.kafka;


import com.bank.msyanki.entity.YankiAccount;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class YankiProducer {
    private final KafkaTemplate<String, YankiAccount> kafkaTemplate;

    public YankiProducer(KafkaTemplate<String, YankiAccount> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTransaction(YankiAccount transaction) {
        kafkaTemplate.send("yanki-transactions", transaction);
    }
}
