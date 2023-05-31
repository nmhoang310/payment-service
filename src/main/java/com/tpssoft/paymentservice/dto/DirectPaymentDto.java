package com.tpssoft.paymentservice.dto;

import lombok.Data;

@Data
public class DirectPaymentDto {
	private Double AMT;
	private String CREDITCARDTYPE;
	private String ACCT;
	private String EXPDATE;
	private String CVV2;
	private String FIRSTNAME;
	private String LASTNAME;
//	private String STREET;
//	private String CITY;
//	private String STATE;
//	private String ZIP;
//	private String COUNTRYCODE;
}
