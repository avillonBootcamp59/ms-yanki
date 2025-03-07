package com.bank.msyanki.repository;

import com.bank.msyanki.entity.YankiAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface YankiRepository extends ReactiveMongoRepository<YankiAccount, String> {
    Mono<YankiAccount> findByPhoneNumber(String phoneNumber);
}

