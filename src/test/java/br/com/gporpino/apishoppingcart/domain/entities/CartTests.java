package br.com.gporpino.apishoppingcart.domain.entities;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartTests {

  @Test
  public void whenHasNoProducts() {
    final Cart subject = new Cart();

    // assert statements
    assertEquals(0, subject.getItems().size());
    assertEquals(0, subject.getSubtotal());
    assertEquals(0, subject.getTotal());
    assertEquals(0, subject.getDiscount());
  }

  @Test
  public void whenTotalIsLessThanAThousand() {
    final Cart subject = new Cart();

    var products = buildProducts(5, 100, true);
    products.forEach(p -> subject.addProduct(p));

    var total = products.stream().mapToInt(p -> p.getPrice()).sum();

    // assert statements
    assertEquals(total, subject.getSubtotal());
    assertEquals(total, subject.getTotal());
  }

  @Test
  public void whenTotalIsMoreThanAThousand() {
    final Cart subject = new Cart();

    var products = buildProducts(5, 200, true);
    products.forEach(p -> subject.addProduct(p));

    var subtotal = products.stream().mapToInt(p -> p.getPrice()).sum();
    var total = subtotal - (subtotal * 5 / 100);
    // assert statements
    assertEquals(subtotal, subject.getSubtotal());
    assertEquals(total, subject.getTotal());
  }

  @Test
  public void whenTotalIsMoreThanFiveThousands() {
    final Cart subject = new Cart();

    var products = buildProducts(5, 1000, true);
    products.forEach(p -> subject.addProduct(p));

    var subtotal = products.stream().mapToInt(p -> p.getPrice()).sum();
    var total = subtotal - (subtotal * 7 / 100);
    // assert statements
    assertEquals(subtotal, subject.getSubtotal());
    assertEquals(total, subject.getTotal());
  }

  @Test
  public void whenTotalIsMoreThanTenThousands() {
    final Cart subject = new Cart();

    var products = buildProducts(5, 2000, true);
    products.forEach(p -> subject.addProduct(p));

    var subtotal = products.stream().mapToInt(p -> p.getPrice()).sum();
    var total = subtotal - (subtotal * 10 / 100);
    // assert statements
    assertEquals(subtotal, subject.getSubtotal());
    assertEquals(total, subject.getTotal());
  }

  @Test
  public void whenWithoutCoupon() {
    final Cart subject = new Cart();

    var products = buildProducts(5);
    products.forEach(p -> subject.addProduct(p));

    var subtotal = products.stream().mapToInt(p -> p.getPrice()).sum();

    // assert statements
    assertEquals(subtotal, subject.getSubtotal());
    assertEquals(subtotal, subject.getTotal());
    assertEquals(0, subject.getDiscount());
  }

  @Test
  public void whenAddCoupon() {
    final Cart subject = new Cart();

    var products = buildProducts(5);
    products.forEach(p -> subject.addProduct(p));

    var coupon = new Coupon(1, "INFLUENCER_MARY10", 10);

    subject.addCoupon(coupon);

    var subtotal = products.stream().mapToInt(p -> p.getPrice()).sum();
    var total = subtotal - (subtotal * 10 / 100);
    // assert statements
    assertEquals(subtotal, subject.getSubtotal());
    assertEquals(total, subject.getTotal());
  }

  @Test
  public void whenAddTwoCouponShouldBeOnlyTheMax() {
    final Cart subject = new Cart();

    var products = buildProducts(5);
    products.forEach(p -> subject.addProduct(p));

    subject.addCoupon(new Coupon(1, "INFLUENCER_MARY10", 10));
    subject.addCoupon(new Coupon(1, "INFLUENCER_MARY15", 15));

    var subtotal = products.stream().mapToInt(p -> p.getPrice()).sum();
    var total = subtotal - (subtotal * 15 / 100);
    // assert statements
    assertEquals(subtotal, subject.getSubtotal());
    assertEquals(total, subject.getTotal());
  }

  @Test
  public void whenAddTwoCouponShouldBeOnlyTheMaxInversePosition() {
    final Cart subject = new Cart();

    var products = buildProducts(5);
    products.forEach(p -> subject.addProduct(p));

    subject.addCoupon(new Coupon(1, "INFLUENCER_MARY15", 20));
    subject.addCoupon(new Coupon(1, "INFLUENCER_MARY10", 10));

    var subtotal = products.stream().mapToInt(p -> p.getPrice()).sum();
    var total = subtotal - (subtotal * 20 / 100);
    // assert statements
    assertEquals(subtotal, subject.getSubtotal());
    assertEquals(total, subject.getTotal());
  }

  @Test
  public void whenHasAtLeastTenSameProduct() {
    final Cart subject = new Cart();

    var products = buildProducts(10, true);
    products.forEach(p -> subject.addProduct(p));

    // assert statements
    assertEquals(1, subject.getItems().size());
    assertEquals(90, subject.getSubtotal());
    assertEquals(90, subject.getTotal());
    assertEquals(0, subject.getDiscount());
  }

  @Test
  public void whenAddTheSameProduct() {
    final Cart subject = new Cart();

    var products = buildProducts(2, true);
    products.forEach(p -> subject.addProduct(p));

    // assert statements
    assertEquals(1, subject.getItems().size());
    assertEquals(20, subject.getSubtotal());
    assertEquals(20, subject.getTotal());
    assertEquals(0, subject.getDiscount());
  }

  @Test
  public void whenHasLessThanTenProducts() {
    final Cart subject = new Cart();

    var products = buildProducts(9);
    products.forEach(p -> subject.addProduct(p));

    // assert statements
    assertEquals(9, subject.getItems().size());
    assertEquals(90, subject.getSubtotal());
    assertEquals(90, subject.getTotal());
  }

  // private methods

  private List<Product> buildProducts(final int size, int price, boolean sameId) {
    List<Product> products = new ArrayList<Product>();

    IntStream.range(0, size).forEach(i -> {
      final Product product = new Product();
      product.setId(sameId ? 1 : i);
      product.setPrice(price);
      products.add(product);
    });

    return products;
  }

  private List<Product> buildProducts(final int size) {
    return buildProducts(size, 10);
  }

  private List<Product> buildProducts(final int size, boolean sameId) {
    return buildProducts(size, 10, sameId);
  }

  private List<Product> buildProducts(final int size, int price) {
    return buildProducts(size, 10, false);
  }
}