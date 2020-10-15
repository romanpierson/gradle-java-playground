package com.inre.test.transaction;

import java.math.BigDecimal;
import java.util.UUID;

import com.inre.test.transaction.exception.TransactionInvalidAmount;

public abstract class AbstractTransaction {

	private final String transactionId;
	protected final BigDecimal transactionAmount;

	protected AbstractTransaction(final BigDecimal transactionAmount) throws TransactionInvalidAmount {

		if(transactionAmount == null || transactionAmount.doubleValue() < 0) {
			throw new TransactionInvalidAmount();
		}
		
		this.transactionId = UUID.randomUUID().toString();
		this.transactionAmount = transactionAmount;

	}

	public abstract BigDecimal calculateTransactionResult(BigDecimal value);

	public String getTransactionId() {
		return transactionId;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

}
