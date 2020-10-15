package com.inre.test;

import java.math.BigDecimal;
import java.util.HashSet;

import com.inre.test.exception.AccountNegativeAmountCreationException;
import com.inre.test.exception.TransactionCauseNegativeAmountException;
import com.inre.test.exception.TransactionExistsException;
import com.inre.test.transaction.AbstractTransaction;

/**
 * 
 * An account instance
 * 
 * Holds an amount and can be modfied via Transactions
 * 
 * Does not allow negative amounts - transactions leading to this will be
 * rejected
 * 
 * @author piersrom
 *
 */
public class Account {

	private BigDecimal amount;
	private HashSet<String> appliedTransactions = new HashSet<>();

	/**
	 * 
	 * Creates a new Account instance
	 * 
	 * @param initialAmount The initial amount - must be greater than 0
	 * @throws AccountNegativeAmountCreationException
	 */
	public Account(final BigDecimal initialAmount) throws AccountNegativeAmountCreationException {

		if (initialAmount == null || initialAmount.doubleValue() < 0.0d) {
			throw new AccountNegativeAmountCreationException();
		}

		this.amount = initialAmount;
	}

	/**
	 * 
	 * Applies an instance of {@link AbstractTransaction} on the account
	 * 
	 * @param transaction		
	 * @throws TransactionExistsException
	 * @throws TransactionCauseNegativeAmountException
	 */
	public void applyTransaction(final AbstractTransaction transaction)
			throws TransactionExistsException, TransactionCauseNegativeAmountException {

		synchronized (this) {
			if (appliedTransactions.contains(transaction.getTransactionId())) {
				throw new TransactionExistsException(transaction.getTransactionId());
			}

			final BigDecimal targetAmount = transaction.calculateTransactionResult(this.amount);

			if (targetAmount.doubleValue() < 0.0d) {
				throw new TransactionCauseNegativeAmountException(transaction.getTransactionId(),
						transaction.getTransactionAmount());
			}

			this.amount = targetAmount;
			this.appliedTransactions.add(transaction.getTransactionId());
		}
	}

	public BigDecimal getAmount() {

		synchronized (this) {
			return this.amount;
		}

	}

}
