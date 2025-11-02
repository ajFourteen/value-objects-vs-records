package de.fourteen.valueObjectsVsRecords.amountAsInt.moneyAsRecord;

import java.math.BigDecimal;
import java.util.Currency;

record Money(int amount, Currency currency) {
  Money {
    if(amount < 0) {
      throw new IllegalArgumentException("Amount musst be positive");
    }
  }
}
