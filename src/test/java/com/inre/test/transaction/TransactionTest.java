package com.inre.test.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.inre.test.exception.AccountNegativeAmountCreationException;
import com.inre.test.transaction.CreditTransaction;
import com.inre.test.transaction.DebitTransaction;
import com.inre.test.transaction.exception.TransactionInvalidAmount;

class TransactionTest {

	@Test
	void test_createTransaction_invalidAmount() {

		assertThrows(TransactionInvalidAmount.class, () -> {
			new DebitTransaction(null);
		});

		assertThrows(TransactionInvalidAmount.class, () -> {
			new DebitTransaction(BigDecimal.valueOf(-0.5d));
		});

		assertThrows(TransactionInvalidAmount.class, () -> {
			new CreditTransaction(null);
		});

		assertThrows(TransactionInvalidAmount.class, () -> {
			new CreditTransaction(BigDecimal.valueOf(-0.5d));
		});
	}

	@Test
	void test_createDebitTransaction_validAmount() throws AccountNegativeAmountCreationException, TransactionInvalidAmount {

		// when
		final DebitTransaction debitTx = new DebitTransaction(BigDecimal.valueOf(100.0d));

		// then
		assertEquals(100.0d, debitTx.getTransactionAmount().doubleValue());

	}

	@Test
	void test_createCreditTransaction_validAmount() throws AccountNegativeAmountCreationException, TransactionInvalidAmount {

		// when
		final CreditTransaction creditTx = new CreditTransaction(BigDecimal.valueOf(100.0d));

		// then
		assertEquals(100.0d, creditTx.getTransactionAmount().doubleValue());

	}

}
