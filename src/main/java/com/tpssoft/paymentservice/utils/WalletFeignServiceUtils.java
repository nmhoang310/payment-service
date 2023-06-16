package com.tpssoft.paymentservice.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tpssoft.paymentservice.dto.CardDto;
import com.tpssoft.paymentservice.dto.TransactionDto;

import feign.Headers;
import feign.Param;

@FeignClient(value = "wallet", url = "http://localhost:8080/api/v0/wallet")
public interface WalletFeignServiceUtils {
	@GetMapping("/card/information/{userId}/{cardId}")
	CardDto getInformationOfCard(@RequestHeader("Authorization") String idToken, @PathVariable(name = "userId") String userId, @PathVariable(name = "cardId") String cardId);
	
	@PutMapping("/balance/update")
	ResponseEntity<String> updateBalance(@RequestHeader("Authorization") String idToken , @RequestParam String userId, @RequestParam double amount);
	
	@GetMapping("/get-wallet-id/{userId}")
	ResponseEntity<String> getWalletId(@RequestHeader("Authorization") String idToken, @PathVariable(name = "userId") String userId);
}
