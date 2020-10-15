package com.inre.test.transaction;

import java.math.BigDecimal;

import com.inre.test.transaction.exception.TransactionInvalidAmount;

/**
 * 
 * A debit transaction - is taken from an account
 * 
 * @author piersrom
 *
 */
public class DebitTransaction extends AbstractTransaction {

	public DebitTransaction(final BigDecimal transactionAmount) throws TransactionInvalidAmount {

		super(transactionAmount);

	}

	@Override
	public BigDecimal calculateTransactionResult(final BigDecimal value) {

		return value.subtract(this.transactionAmount);

	}

}
