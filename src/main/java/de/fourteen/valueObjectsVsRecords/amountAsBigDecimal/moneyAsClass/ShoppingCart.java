package de.fourteen.valueObjectsVsRecords.amountAsBigDecimal.moneyAsClass;

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
        .anyMatch(cardItem -> cardItem.price().hasCurrency(expectedCurrency));
    if (!allCardItemsPricesHaveExpectedCurrency) {
      throw new IllegalStateException(
          "At least one card item has an unexpected currency");
    }
    return cardItems
        .stream()
        .map(CardItem::price)
        .reduce(new Money(BigDecimal.ZERO, expectedCurrency), Money::add);
  }
}
