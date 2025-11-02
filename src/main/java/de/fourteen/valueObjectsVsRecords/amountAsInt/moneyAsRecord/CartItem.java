package de.fourteen.valueObjectsVsRecords.amountAsInt.moneyAsRecord;

final class CartItem {
  private final Money
      price;
  private final String name;

  CartItem(final Money price, final String name) {
    this.price = price;
    this.name = name;
  }
  
  Money price() {
    return price;
  }
}
