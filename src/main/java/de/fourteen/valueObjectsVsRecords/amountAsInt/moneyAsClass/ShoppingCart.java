package de.fourteen.valueObjectsVsRecords.amountAsInt.moneyAsClass;

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
        .reduce(new Money(0, expectedCurrency), Money::add);
  }
}
