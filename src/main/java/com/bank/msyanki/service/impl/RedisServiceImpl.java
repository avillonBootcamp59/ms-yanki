package com.bank.msyanki.service.impl;


import com.bank.msyanki.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final ReactiveRedisTemplate<String, String> redisTemplate;

    @Override
    public Mono<Void> cacheBalance(String walletId, Double balance) {
        return redisTemplate.opsForValue()
                .set("wallet_balance:" + walletId, balance.toString(), Duration.ofMinutes(5))
                .then();
    }
    @Override
    public Mono<Double> getCachedBalance(String walletId) {
        return redisTemplate.opsForValue()
                .get("wallet_balance:" + walletId)
                .map(Double::parseDouble)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> removeCachedBalance(String walletId) {
        return redisTemplate.delete("wallet_balance:" + walletId).then();
    }
}
