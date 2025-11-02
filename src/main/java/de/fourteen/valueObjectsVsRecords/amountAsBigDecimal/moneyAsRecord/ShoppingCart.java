package de.fourteen.valueObjectsVsRecords.amountAsBigDecimal.moneyAsRecord;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;

final class ShoppingCart {
  private final Collection<CartItem> CartItems;

  ShoppingCart(final Collection<CartItem> CartItems) {
    this.CartItems = CartItems;
  }

  Money totalAmount(Currency expectedCurrency) {
    boolean allCartItemsPricesHaveExpectedCurrency = CartItems
        .stream()
        .anyMatch(CartItem -> CartItem.price().currency().equals(expectedCurrency));
    if (!allCartItemsPricesHaveExpectedCurrency) {
      throw new IllegalStateException(
          "At least one card item has an unexpected currency");
    }
    BigDecimal totalAmount = CartItems
        .stream()
        .map(CartItem -> CartItem.price().amount())
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    return new Money(totalAmount, expectedCurrency);
  }
}
