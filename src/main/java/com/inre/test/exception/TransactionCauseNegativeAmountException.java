package com.inre.test.exception;

import java.math.BigDecimal;

public class TransactionCauseNegativeAmountException extends Exception {

	private static final long serialVersionUID = 1L;

	public TransactionCauseNegativeAmountException(final String transactionId, final BigDecimal transactionAmount) {
		super(String.format("Transaction with ID %s and amount %s leads to a negative amount on the given account",
				transactionId, transactionAmount.toString()));
	}
}
