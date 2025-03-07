package com.bank.msyanki.service;

import reactor.core.publisher.Mono;

public interface RedisService {
    public Mono<Void> cacheBalance(String walletId, Double balance);
    public Mono<Double> getCachedBalance(String walletId);
    public Mono<Void> removeCachedBalance(String walletId);
}

