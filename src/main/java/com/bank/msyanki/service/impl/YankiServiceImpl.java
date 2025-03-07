package com.bank.msyanki.service.impl;

import com.bank.msyanki.entity.YankiAccount;
import com.bank.msyanki.repository.YankiRepository;
import com.bank.msyanki.service.YankiService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class YankiServiceImpl implements YankiService {

    private final YankiRepository yankiRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Override
    public Single<YankiAccount> createAccount(YankiAccount account) {
        return Single.fromPublisher(yankiRepository.save(account));
    }

    @Override
    public Single<YankiAccount> getAccountByPhone(String phone) {
        return Single.fromPublisher(yankiRepository.findByPhoneNumber(phone));
    }

    @Override
    public Mono<Void> sendPayment(String senderPhone, String receiverPhone, Double amount) {
        return yankiRepository.findByPhoneNumber(senderPhone)
                .flatMap(sender -> {
                    if (sender.getBalance() < amount) {
                        return Mono.error(new RuntimeException("Saldo insuficiente"));
                    }
                    sender.setBalance(sender.getBalance() - amount);
                    return yankiRepository.save(sender)
                            .then(yankiRepository.findByPhoneNumber(receiverPhone))
                            .flatMap(receiver -> {
                                receiver.setBalance(receiver.getBalance() + amount);
                                kafkaTemplate.send("yanki-transactions", "Pago enviado de " + senderPhone + " a " + receiverPhone);
                                return yankiRepository.save(receiver).then();
                            });
                });
    }


}
