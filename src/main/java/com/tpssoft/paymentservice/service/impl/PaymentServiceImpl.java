package com.tpssoft.paymentservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpssoft.paymentservice.logic.IPaymentLogic;
import com.tpssoft.paymentservice.service.IPaymentService;

@Service
public class PaymentServiceImpl implements IPaymentService {
	@Autowired
	private IPaymentLogic paymentLogic;

	@Override
	public boolean topUp(String userId, double amount) {
		return paymentLogic.topUp(userId, amount);
	}

}
