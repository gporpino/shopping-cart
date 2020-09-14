package br.com.gporpino.apishoppingcart.domain.enums;

public enum AmountDiscount {

  START(5, 1000, 4999), MEDIUM(7, 5000, 9999), EXTREME(10, 10000, (int) Double.POSITIVE_INFINITY);

  private int discount;
  private int from;
  private int to;

  AmountDiscount(int discount, int from, int to) {
    this.discount = discount;
    this.from = from;
    this.to = to;
  }

  public int discount() {
    return this.discount;
  }

  public int from() {
    return this.from;
  }

  public int to() {
    return this.to;
  }
}
