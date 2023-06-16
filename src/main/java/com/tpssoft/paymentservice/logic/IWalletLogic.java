package com.tpssoft.paymentservice.logic;

import org.springframework.stereotype.Component;

import com.tpssoft.paymentservice.dto.CardDto;

@Component
public interface IWalletLogic {

	boolean updateBalance(String idToken, String ack, String userId, double amount);

	CardDto getCardInformation(String idToken, String userId, String cardId);

	String getWalletId(String idToken, String userId);

}
