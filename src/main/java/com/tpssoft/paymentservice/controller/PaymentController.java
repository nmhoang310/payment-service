package com.tpssoft.paymentservice.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpssoft.paymentservice.service.IPaymentService;

@RestController
@RequestMapping("api/v0/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {
	
	
	@Autowired
	private IPaymentService paymentService;
	
	@PostMapping("top-up")
    public ResponseEntity<String> topUp(@RequestParam String userId, @RequestParam double amount) {
		if (paymentService.topUp(userId, amount))
			return ResponseEntity.status(200).body("Top up successful!");
		return ResponseEntity.status(501).body("Top up fail");
	}
}
