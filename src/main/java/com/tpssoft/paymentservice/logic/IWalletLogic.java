package com.tpssoft.paymentservice.logic;

import org.springframework.stereotype.Component;

import com.tpssoft.paymentservice.dto.CardDto;

@Component
public interface IWalletLogic {

	boolean updateBalance(String ack, String userId, double amount);

	CardDto getCardInformation(String userId);

	String getWalletId(String userId);

}
