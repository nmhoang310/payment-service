package com.tpssoft.paymentservice.logic;

import org.springframework.stereotype.Component;

@Component
public interface IPaymentLogic {
	public boolean topUp(String idToken, String userId, String cardId, double amount);
}
