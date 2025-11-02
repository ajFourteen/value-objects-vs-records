package de.fourteen.valueObjectsVsRecords.amountAsBigDecimal.moneyAsClass;

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
        .anyMatch(CartItem -> CartItem.price().hasCurrency(expectedCurrency));
    if (!allCartItemsPricesHaveExpectedCurrency) {
      throw new IllegalStateException(
          "At least one card item has an unexpected currency");
    }
    return CartItems
        .stream()
        .map(CartItem::price)
        .reduce(new Money(BigDecimal.ZERO, expectedCurrency), Money::add);
  }
}
