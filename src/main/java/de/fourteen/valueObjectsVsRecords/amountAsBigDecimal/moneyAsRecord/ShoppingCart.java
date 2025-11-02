package de.fourteen.valueObjectsVsRecords.amountAsBigDecimal.moneyAsRecord;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;

final class ShoppingCart {
  private final Collection<CardItem> cardItems;

  ShoppingCart(final Collection<CardItem> cardItems) {
    this.cardItems = cardItems;
  }

  Money totalAmount(Currency expectedCurrency) {
    boolean allCardItemsPricesHaveExpectedCurrency = cardItems
        .stream()
        .anyMatch(cardItem -> cardItem.price().currency().equals(expectedCurrency));
    if (!allCardItemsPricesHaveExpectedCurrency) {
      throw new IllegalStateException(
          "At least one card item has an unexpected currency");
    }
    BigDecimal totalAmount = cardItems
        .stream()
        .map(cardItem -> cardItem.price().amount())
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    return new Money(totalAmount, expectedCurrency);
  }
}
