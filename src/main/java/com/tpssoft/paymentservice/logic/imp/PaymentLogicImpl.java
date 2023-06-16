package com.tpssoft.paymentservice.logic.imp;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tpssoft.paymentservice.constant.TransactionStatus;
import com.tpssoft.paymentservice.dto.CardDto;
import com.tpssoft.paymentservice.dto.PaypalDirectPaymentResponseDto;
import com.tpssoft.paymentservice.logic.IPaymentLogic;
import com.tpssoft.paymentservice.logic.IPaypalLogic;
import com.tpssoft.paymentservice.logic.ITransactionLogic;
import com.tpssoft.paymentservice.logic.IWalletLogic;

@Component
public class PaymentLogicImpl implements IPaymentLogic {
	public static final double RATE = 2.89/100;
	public static final double FIXEDFEE = 0.49;

	@Autowired
	private ITransactionLogic transactionLogic;
	@Autowired
	private IWalletLogic walletLogic;
	@Autowired
	private IPaypalLogic paypalLogic;

	public boolean topUp(String idToken, String userId, String cardId, double amount) {
		// *****validate amount
		// ***userid

		/* get card */
		CardDto cardDto = walletLogic.getCardInformation(idToken, userId, cardId);

		/* request to direct payment paypal api */
		double amountNotRound = (amount + FIXEDFEE)/(1-RATE);
		double amountSendToPaypal = Math.round(amountNotRound * 100.0)/100.0;
		double fee = Math.round((amountSendToPaypal * RATE + FIXEDFEE) * 100.0)/100.0;
		PaypalDirectPaymentResponseDto directPaymentResponse = paypalLogic.sendDirectPaymentRequest(cardDto, amountSendToPaypal);

		/* update balance */
		if (!walletLogic.updateBalance(idToken, directPaymentResponse.getAck(), userId, amount)) {
			System.out.println("Update Balance Fail");
			return false;
		}

		/*create transaction*/
		String transactionId = directPaymentResponse.getTransactionid();
		LocalDateTime transactionDate = LocalDateTime.now();
		String category = "Top up";
		String status = TransactionStatus.COMPLETED.toString();
		String walletId = walletLogic.getWalletId(idToken, userId);
		String beneficiary = cardDto.getCardNumber().substring(cardDto.getCardNumber().length() - 4);
		String remitter = cardDto.getFirstName() + " " + cardDto.getLastName();
				
		
		boolean resultCreateTransaction = transactionLogic.createTransaction(idToken, transactionId, transactionDate, category, status, cardId, walletId, beneficiary, remitter, fee, amount);
		if (!resultCreateTransaction) {
			System.out.println("Create Transaction Fail");
			return false;
		}

		return true;
	}
}
