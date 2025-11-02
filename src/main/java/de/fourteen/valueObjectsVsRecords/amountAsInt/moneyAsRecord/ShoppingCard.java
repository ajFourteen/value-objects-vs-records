package de.fourteen.valueObjectsVsRecords.amountAsInt.moneyAsRecord;

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
        .anyMatch(cardItem -> cardItem.price().currency().equals(expectedCurrency));
    if (!allCardItemsPricesHaveExpectedCurrency) {
      throw new IllegalStateException(
          "At least one card item has an unexpected currency");
    }
    int totalAmount = cardItems
        .stream()
        .map(cardItem -> cardItem.price().amount())
        .reduce(0, Integer::sum);
    return new Money(totalAmount, expectedCurrency);
  }
}
