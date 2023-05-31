package com.tpssoft.paymentservice.logic;

import org.springframework.stereotype.Component;

@Component
public interface IPaymentLogic {
	public boolean topUp(String userId, double amount);
}
