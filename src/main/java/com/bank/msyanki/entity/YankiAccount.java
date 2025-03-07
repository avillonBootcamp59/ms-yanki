package com.bank.msyanki.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "yanki_accounts")
public class YankiAccount {
    @Id
    private String id;
    private String documentType;
    private String documentNumber;
    private String phoneNumber;
    private String imei;
    private String email;
    private String debitCard;
    private Double balance;
}
