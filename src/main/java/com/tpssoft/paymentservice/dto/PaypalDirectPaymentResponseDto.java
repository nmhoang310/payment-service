package com.tpssoft.paymentservice.dto;

import java.lang.reflect.Field;

import lombok.Data;

@Data
public class PaypalDirectPaymentResponseDto {
	private String timestamp;
	private String correlationid;
	private String ack;
	private String amt;
	private String version;
	private String build;
	private String currencycode;
	private String avscode;
	private String cvv2match;
	private String transactionid;
	
	public static PaypalDirectPaymentResponseDto convertToObject(String input) {
		String[] keyValuePairs = input.split("&");
		PaypalDirectPaymentResponseDto dataObject = new PaypalDirectPaymentResponseDto();
		
		for (String keyValuePair : keyValuePairs) {
		    String[] parts = keyValuePair.split("=");
		    if (parts.length == 2) {
		        String key = parts[0];		        
		        String value = parts[1];		        
		        try {
                    Field field = dataObject.getClass().getDeclaredField(key.toLowerCase());
                    field.setAccessible(true);
                    field.set(dataObject, value);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
		    }
		}
		return dataObject;
	}
}
