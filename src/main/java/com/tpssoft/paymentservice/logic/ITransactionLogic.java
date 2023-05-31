package com.tpssoft.paymentservice.logic;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public interface ITransactionLogic {

	boolean createTransaction(String transactionId, LocalDateTime transactionDate, String category, String status, String cardId, String walletId, String remitter,
			String beneficiary, double fee, double amount);

}
