package de.fourteen.valueObjectsVsRecords.amountAsInt.moneyAsClass;

import java.util.Currency;

final class Money {
  private final int amount;
  private final Currency currency;

  Money(final int amount, final Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  boolean hasCurrency(Currency expectedCurrency) {
    return currency.equals(expectedCurrency);
  }

  Money add(Money other) {
    if(!other.hasCurrency(currency)) {
      throw new IllegalArgumentException("Can't add amounts with different currencies");
    }
    return new Money(amount + other.amount, currency);
  }
}
