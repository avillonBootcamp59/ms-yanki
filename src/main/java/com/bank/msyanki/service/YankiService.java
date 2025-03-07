package com.bank.msyanki.service;

import com.bank.msyanki.entity.YankiAccount;
import io.reactivex.rxjava3.core.Single;
import reactor.core.publisher.Mono;

public interface YankiService {
    public Single<YankiAccount> createAccount(YankiAccount account);
    public Single<YankiAccount> getAccountByPhone(String phone);
    public Mono<Void> sendPayment(String senderPhone, String receiverPhone, Double amount);
}
