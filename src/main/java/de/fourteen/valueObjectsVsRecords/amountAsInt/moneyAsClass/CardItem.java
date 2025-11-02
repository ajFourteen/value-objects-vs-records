package de.fourteen.valueObjectsVsRecords.amountAsInt.moneyAsClass;

final class CardItem {
  private final Money
      price;
  private final String name;

  CardItem(final Money price, final String name) {
    this.price = price;
    this.name = name;
  }

  Money price() {
    return price;
  }
}
