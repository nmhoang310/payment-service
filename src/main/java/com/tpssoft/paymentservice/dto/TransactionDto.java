package com.tpssoft.paymentservice.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransactionDto {
	private String transactionId;
	
	private Double amount;

	private Double fee;

	private String category;

	private String remitter;

	private String beneficiary;

	private String status;

	private String walletId;

	private String cardId;

	private LocalDateTime transactionDate;
}
