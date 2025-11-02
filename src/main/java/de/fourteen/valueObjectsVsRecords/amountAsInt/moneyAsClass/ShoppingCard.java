package de.fourteen.valueObjectsVsRecords.amountAsInt.moneyAsClass;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;

final class ShoppingCard {
  private final Collection<CardItem> cardItems;

  ShoppingCard(final Collection<CardItem> cardItems) {
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
        .reduce(new Money(0, expectedCurrency), Money::add);
  }
}
