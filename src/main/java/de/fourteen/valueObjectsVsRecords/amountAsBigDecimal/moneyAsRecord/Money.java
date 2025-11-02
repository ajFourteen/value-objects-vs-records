package de.fourteen.valueObjectsVsRecords.amountAsBigDecimal.moneyAsRecord;

import java.math.BigDecimal;
import java.util.Currency;

record Money(BigDecimal amount, Currency currency) {
  Money {
    if(amount.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Amount musst be positive");
    }
  }
}
