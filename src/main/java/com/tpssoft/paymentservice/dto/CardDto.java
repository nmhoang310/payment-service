package com.tpssoft.paymentservice.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CardDto {
	private String cardId;
	private String firstName;
	private String lastName;
	private String cardNumber;
	private LocalDate expDate;
	private String cvv;
	private String billingAddr;	
	private String cardType;
}
