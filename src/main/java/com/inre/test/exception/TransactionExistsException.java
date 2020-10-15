package com.inre.test.exception;

public class TransactionExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public TransactionExistsException(final String transactionId) {
		super(String.format("Transaction with ID %s already exists for the given account", transactionId));
	}
}
