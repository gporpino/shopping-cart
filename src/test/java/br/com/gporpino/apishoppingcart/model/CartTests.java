package br.com.gporpino.apishoppingcart.model;

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
    assertEquals(0, subject.subtotal());
    assertEquals(0, subject.total());
    assertEquals(0, subject.discount());
  }

  @Test
  public void whenTotalIsLessThanAThousand() {
    final Cart subject = new Cart();

    var products = buildProducts(5, 100);
    products.forEach(p -> subject.addProduct(p));

    var total = products.stream().mapToInt(p -> p.getPrice()).sum();

    // assert statements
    assertEquals(total, subject.subtotal());
    assertEquals(total, subject.total());
  }

  @Test
  public void whenTotalIsMoreThanAThousand() {
    final Cart subject = new Cart();

    var products = buildProducts(5, 200);
    products.forEach(p -> subject.addProduct(p));

    var subtotal = products.stream().mapToInt(p -> p.getPrice()).sum();
    var total = subtotal - (subtotal * 5 / 100);
    // assert statements
    assertEquals(subtotal, subject.subtotal());
    assertEquals(total, subject.total());
  }

  @Test
  public void whenTotalIsMoreThanFiveThousands() {
    final Cart subject = new Cart();

    var products = buildProducts(5, 1000);
    products.forEach(p -> subject.addProduct(p));

    var subtotal = products.stream().mapToInt(p -> p.getPrice()).sum();
    var total = subtotal - (subtotal * 7 / 100);
    // assert statements
    assertEquals(subtotal, subject.subtotal());
    assertEquals(total, subject.total());
  }

  @Test
  public void whenTotalIsMoreThanTenThousands() {
    final Cart subject = new Cart();

    var products = buildProducts(5, 2000);
    products.forEach(p -> subject.addProduct(p));

    var subtotal = products.stream().mapToInt(p -> p.getPrice()).sum();
    var total = subtotal - (subtotal * 10 / 100);
    // assert statements
    assertEquals(subtotal, subject.subtotal());
    assertEquals(total, subject.total());
  }

  @Test
  public void whenWithoutCoupon() {
    final Cart subject = new Cart();

    var products = buildProducts(5);
    products.forEach(p -> subject.addProduct(p));

    var subtotal = products.stream().mapToInt(p -> p.getPrice()).sum();

    // assert statements
    assertEquals(subtotal, subject.subtotal());
    assertEquals(subtotal, subject.total());
    assertEquals(0, subject.discount());
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
    assertEquals(subtotal, subject.subtotal());
    assertEquals(total, subject.total());
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
    assertEquals(subtotal, subject.subtotal());
    assertEquals(total, subject.total());
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
    assertEquals(subtotal, subject.subtotal());
    assertEquals(total, subject.total());
  }

  @Test
  public void whenHasAtLeastTenProducts() {
    final Cart subject = new Cart();

    var products = buildProducts(10);
    products.forEach(p -> subject.addProduct(p));

    // assert statements
    assertEquals(10, subject.getItems().size());
    assertEquals(90, subject.subtotal());
    assertEquals(90, subject.total());
    assertEquals(0, subject.discount());
  }

  @Test
  public void whenHasLessThanTenProducts() {
    final Cart subject = new Cart();

    var products = buildProducts(9);
    products.forEach(p -> subject.addProduct(p));

    // assert statements
    assertEquals(9, subject.getItems().size());
    assertEquals(90, subject.subtotal());
    assertEquals(90, subject.total());
  }

  // private methods

  private List<Product> buildProducts(final int size, int price) {
    List<Product> products = new ArrayList<Product>();

    IntStream.range(0, size).forEach(i -> {
      final Product product = new Product();
      product.setPrice(price);
      products.add(product);
    });

    return products;
  }

  private List<Product> buildProducts(final int size) {
    return buildProducts(size, 10);
  }
}