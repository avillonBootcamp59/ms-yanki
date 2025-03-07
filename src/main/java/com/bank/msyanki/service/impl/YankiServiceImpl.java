package com.bank.msyanki.service.impl;

import com.bank.msyanki.entity.YankiAccount;
import com.bank.msyanki.repository.YankiRepository;
import com.bank.msyanki.service.YankiService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YankiServiceImpl implements YankiService {

    private final YankiRepository yankiRepository;

    @Override
    public Single<YankiAccount> createAccount(YankiAccount account) {
        return Single.fromPublisher(yankiRepository.save(account));
    }

    @Override
    public Single<YankiAccount> getAccountByPhone(String phone) {
        return Single.fromPublisher(yankiRepository.findByPhoneNumber(phone));
    }
}
