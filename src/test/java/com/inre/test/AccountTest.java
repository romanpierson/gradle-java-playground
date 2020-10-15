package com.inre.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.inre.test.Account;
import com.inre.test.exception.AccountNegativeAmountCreationException;
import com.inre.test.exception.TransactionCauseNegativeAmountException;
import com.inre.test.exception.TransactionExistsException;
import com.inre.test.transaction.CreditTransaction;
import com.inre.test.transaction.DebitTransaction;
import com.inre.test.transaction.exception.TransactionInvalidAmount;

class AccountTest {

	@Test
	void test_createAccount_invalidAmount() {

		assertThrows(AccountNegativeAmountCreationException.class, () -> {
			new Account(null);
		});

		assertThrows(AccountNegativeAmountCreationException.class, () -> {
			new Account(BigDecimal.valueOf(-0.5d));
		});
	}

	@Test
	void test_createAccount_validAmount() throws AccountNegativeAmountCreationException {

		// when
		final Account account = new Account(BigDecimal.valueOf(100.0d));

		// then
		assertEquals(100.0d, account.getAmount().doubleValue());

	}

	@Test
	void test_account_debitoperation_leading_to_neagtive_value() throws AccountNegativeAmountCreationException {

		// when
		final Account account = new Account(BigDecimal.valueOf(100.0d));

		// then
		assertThrows(TransactionCauseNegativeAmountException.class, () -> {
			account.applyTransaction(new DebitTransaction(BigDecimal.valueOf(101.0d)));
		});

		assertEquals(100.0d, account.getAmount().doubleValue());
	}

	@Test
	void test_account_debitoperation_leading_to_valid_value() throws AccountNegativeAmountCreationException,
			TransactionExistsException, TransactionCauseNegativeAmountException, TransactionInvalidAmount {

		// when
		final Account account = new Account(BigDecimal.valueOf(100.0d));

		// then
		account.applyTransaction(new DebitTransaction(BigDecimal.valueOf(99.0d)));

		assertEquals(1.0d, account.getAmount().doubleValue());
	}

	@Test
	void test_account_creditoperation() throws AccountNegativeAmountCreationException, TransactionExistsException,
			TransactionCauseNegativeAmountException, TransactionInvalidAmount {

		// when
		final Account account = new Account(BigDecimal.valueOf(100.0d));

		// then
		account.applyTransaction(new CreditTransaction(BigDecimal.valueOf(99.0d)));

		assertEquals(199.0d, account.getAmount().doubleValue());
	}

	@Test
	void test_account_creditoperation_same_transaction_not_accepted_again()
			throws AccountNegativeAmountCreationException, TransactionExistsException,
			TransactionCauseNegativeAmountException, TransactionInvalidAmount {

		// when
		final Account account = new Account(BigDecimal.valueOf(100.0d));
		final CreditTransaction tx = new CreditTransaction(BigDecimal.valueOf(1.0d));

		account.applyTransaction(tx);
		assertEquals(101.0d, account.getAmount().doubleValue());

		// then
		assertThrows(TransactionExistsException.class, () -> {
			account.applyTransaction(tx);
		});

		assertEquals(101.0d, account.getAmount().doubleValue());

	}

}
