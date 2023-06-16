package com.tpssoft.paymentservice.logic;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public interface ITransactionLogic {

	boolean createTransaction(String idToken, String transactionId, LocalDateTime transactionDate, String category, String status, String cardId, String beneficiary, String walletId, String remitter, double fee, double amount);

}
