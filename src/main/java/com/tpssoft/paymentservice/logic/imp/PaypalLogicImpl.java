package com.tpssoft.paymentservice.logic.imp;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.tpssoft.paymentservice.component.PaypalHttpClient;
import com.tpssoft.paymentservice.dto.CardDto;
import com.tpssoft.paymentservice.dto.DirectPaymentDto;
import com.tpssoft.paymentservice.dto.PaypalDirectPaymentResponseDto;
import com.tpssoft.paymentservice.logic.IPaypalLogic;

@Component
public class PaypalLogicImpl implements IPaypalLogic {
	private final PaypalHttpClient paypalHttpClient;
	
	public PaypalLogicImpl(PaypalHttpClient paypalHttpClient) {
		super();
		this.paypalHttpClient = paypalHttpClient;
	}

	private String convertSingleDigitToDoubleDigitMonths(int month) {
		int singleDigitMonths = 10;
		String doubleDigitMonth = month < singleDigitMonths ? "0" + month : String.valueOf(month);
		return doubleDigitMonth;
	}
	
	@Override
	public PaypalDirectPaymentResponseDto sendDirectPaymentRequest(CardDto cardDto, double amount) {
	
		DirectPaymentDto directPayment = new DirectPaymentDto();
		directPayment.setAMT(amount);
		directPayment.setCREDITCARDTYPE(cardDto.getCardType());
		directPayment.setACCT(cardDto.getCardNumber());
		//convert expDate
		String montConverted = convertSingleDigitToDoubleDigitMonths(cardDto.getExpDate().getMonthValue());
		String expdate = montConverted + cardDto.getExpDate().getYear();
		directPayment.setEXPDATE(expdate);
		directPayment.setCVV2(cardDto.getCvv());
		directPayment.setFIRSTNAME(cardDto.getFirstName());
		directPayment.setLASTNAME(cardDto.getLastName());

		/* request to direct payment paypal api */
		String directPaymentResponseString;
		PaypalDirectPaymentResponseDto directPaymentResponseDto = new PaypalDirectPaymentResponseDto();
		try {
			directPaymentResponseString = paypalHttpClient.doDirectPayment(directPayment);
			directPaymentResponseDto = PaypalDirectPaymentResponseDto.convertToObject(directPaymentResponseString);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return directPaymentResponseDto;
	}
}
