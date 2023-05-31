package com.tpssoft.paymentservice.logic.imp;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tpssoft.paymentservice.dto.TransactionDto;
import com.tpssoft.paymentservice.logic.ITransactionLogic;
import com.tpssoft.paymentservice.utils.AccountingFeignServiceUtils;

@Component
public class TransactionLogicImpl implements ITransactionLogic {
	@Autowired
	private AccountingFeignServiceUtils accountingFeignServiceUtils;
	
	@Override
	public boolean createTransaction(String transactionId, LocalDateTime transactionDate, String category, String status, String cardId, String walletId, String remitter, String beneficiary, double fee, double amount) {
		TransactionDto transaction = new TransactionDto();
		transaction.setTransactionId(transactionId);
		transaction.setAmount(amount);
		transaction.setTransactionDate(transactionDate);
		transaction.setCategory(category);
		transaction.setStatus(status);
		transaction.setCardId(cardId);
		transaction.setWalletId(walletId);		
		transaction.setRemitter(remitter);
		transaction.setBeneficiary(beneficiary);
		transaction.setFee(fee);
		ResponseEntity<String> responseAccounting = accountingFeignServiceUtils.createTransaction(transaction);
		if (responseAccounting.getStatusCode().value() == 200)
			return true;
		return false;
	}
}
