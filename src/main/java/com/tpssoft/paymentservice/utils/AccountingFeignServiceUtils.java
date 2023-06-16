package com.tpssoft.paymentservice.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.tpssoft.paymentservice.dto.TransactionDto;

@FeignClient(value = "accounting", url = "http://localhost:8082/api/v0/accounting")
public interface AccountingFeignServiceUtils {
	@PostMapping("/create-transaction")
	ResponseEntity<String> createTransaction(@RequestHeader("Authorization") String idToken, @RequestBody TransactionDto transactionDto);
}
