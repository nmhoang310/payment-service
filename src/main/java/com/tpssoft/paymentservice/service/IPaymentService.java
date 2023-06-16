package com.tpssoft.paymentservice.service;

public interface IPaymentService {

	boolean topUp(String idToken, String userId, String cardId, double amount);


}
