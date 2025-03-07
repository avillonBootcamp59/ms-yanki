package com.bank.msyanki.controllers;

import com.bank.msyanki.entity.YankiAccount;
import com.bank.msyanki.service.YankiService;
import com.bank.msyanki.service.impl.YankiServiceImpl;
import io.reactivex.rxjava3.core.Single;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/yanki")
public class YankiController {

    private final YankiService yankiService;

    public YankiController(YankiService yankiService) {
        this.yankiService = yankiService;
    }

    @PostMapping("/create")
    public Single<ResponseEntity<YankiAccount>> createAccount(@RequestBody YankiAccount account) {
        return yankiService.createAccount(account)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{phone}")
    public Single<ResponseEntity<YankiAccount>> getAccount(@PathVariable String phone) {
        return yankiService.getAccountByPhone(phone)
                .map(ResponseEntity::ok);
    }


    @PostMapping("/transfer")
    public Mono<ResponseEntity<String>> transferMoney(@RequestParam String senderPhone,
                                                      @RequestParam String receiverPhone,
                                                      @RequestParam Double amount) {
        return yankiService.sendPayment(senderPhone, receiverPhone, amount)
                .then(Mono.just(ResponseEntity.ok("Pago realizado con éxito")));
    }

}
