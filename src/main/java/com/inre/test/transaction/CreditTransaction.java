package com.inre.test.transaction;

import java.math.BigDecimal;

import com.inre.test.transaction.exception.TransactionInvalidAmount;

/**
 * 
 * A credit transaction - adds the given amount to an account
 * 
 * @author piersrom
 *
 */
public class CreditTransaction extends AbstractTransaction {

	public CreditTransaction(final BigDecimal transactionAmount) throws TransactionInvalidAmount {

		super(transactionAmount);

	}

	@Override
	public BigDecimal calculateTransactionResult(final BigDecimal value) {

		return value.add(this.transactionAmount);

	}

}
