package com.tpssoft.paymentservice.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tpssoft.paymentservice.dto.CardDto;
import com.tpssoft.paymentservice.dto.TransactionDto;

@FeignClient(value = "wallet", url = "http://localhost:8080/api/v0/wallet")
public interface WalletFeignServiceUtils {
	@GetMapping("/card/information/{userId}")
	CardDto getInformationOfCard(@PathVariable(name = "userId") String userId);
	
	@PutMapping("/balance/update")
	ResponseEntity<String> updateBalance(@RequestParam String userId, @RequestParam double amount);
	
	@GetMapping("/get-wallet-id/{userId}")
	String getWalletId(@PathVariable(name = "userId") String userId);
}
