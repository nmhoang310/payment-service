package com.tpssoft.paymentservice.logic.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tpssoft.paymentservice.dto.CardDto;
import com.tpssoft.paymentservice.logic.IWalletLogic;
import com.tpssoft.paymentservice.utils.WalletFeignServiceUtils;

@Component
public class WalletLogicImpl implements IWalletLogic {
	@Autowired
	private WalletFeignServiceUtils walletFeignServiceUtils;
	
	@Override
	public CardDto getCardInformation(String userId) {
		CardDto cardDto = walletFeignServiceUtils.getInformationOfCard(userId);
		return cardDto;
	}
	
	@Override
	public boolean updateBalance(String ack, String userId, double amount) {
		if (ack.equals("Success")) {
			ResponseEntity<String> response = walletFeignServiceUtils.updateBalance(userId, amount);
			if (response.getStatusCode().value() == 200)
				return true;			
		}
		return false;
	}
	
	@Override
	public String getWalletId(String userId) {
		return walletFeignServiceUtils.getWalletId(userId);
	}
}
