package de.fourteen.valueObjectsVsRecords.amountAsBigDecimal.moneyAsClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

final class Money {
  private final BigDecimal amount;
  private final Currency currency;

  Money(final BigDecimal amount, final Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  Money(final int amount, final Currency currency) {
    this(
        new BigDecimal(amount).divide(
            new BigDecimal(100),
            2,
            RoundingMode.UNNECESSARY
        ), currency
    );
  }

  boolean hasCurrency(Currency expectedCurrency) {
    return currency.equals(expectedCurrency);
  }

  Money add(Money other) {
    if (!other.hasCurrency(currency)) {
      throw new IllegalArgumentException(
          "Can't add amounts with different currencies");
    }
    return new Money(amount.add(other.amount), currency);
  }
}
