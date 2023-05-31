package com.tpssoft.paymentservice.logic;

import org.springframework.stereotype.Component;

import com.tpssoft.paymentservice.dto.CardDto;
import com.tpssoft.paymentservice.dto.PaypalDirectPaymentResponseDto;

@Component
public interface IPaypalLogic {

	PaypalDirectPaymentResponseDto sendDirectPaymentRequest(CardDto cardDto, double amount);

}
