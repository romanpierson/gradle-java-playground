package com.inre.test.exception;

public class AccountNegativeAmountCreationException extends Exception {

	private static final long serialVersionUID = 1L;

	public AccountNegativeAmountCreationException() {
		super("Account must be created with a value of at least 0");
	}
}
