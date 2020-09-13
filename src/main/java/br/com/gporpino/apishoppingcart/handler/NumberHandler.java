package br.com.gporpino.apishoppingcart.handler;

public class NumberHandler {
  public static boolean isBetween(final int x, final int lower, final int upper) {
    return lower <= x && x <= upper;
  }
}
