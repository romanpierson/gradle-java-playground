package com.inre.test.transaction.exception;

public class TransactionInvalidAmount extends Exception {

	private static final long serialVersionUID = 1L;

	public TransactionInvalidAmount() {
		super("Transaction amount must be greater 0");
	}
}
