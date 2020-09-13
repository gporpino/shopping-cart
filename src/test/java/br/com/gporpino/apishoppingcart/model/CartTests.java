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
  public void WhenHasAtLeastTenProducts() {
    final Cart subject = new Cart();

    var products = buildProducts(10);
    products.forEach(p -> subject.addProduct(p));

    // assert statements
    assertEquals(10, subject.getProducts().size());
    assertEquals(90, subject.getTotal());
  }

  @Test
  public void WhenHasLessThanTenProducts() {
    final Cart subject = new Cart();

    var products = buildProducts(9);
    products.forEach(p -> subject.addProduct(p));

    // assert statements
    assertEquals(9, subject.getProducts().size());
    assertEquals(90, subject.getTotal());
  }

  @Test
  public void WhenTotalIsLessThanAThousand() {
    final Cart subject = new Cart();

    var products = buildProducts(5, 100);
    products.forEach(p -> subject.addProduct(p));

    var total = products.stream().mapToInt(p -> p.getPrice()).sum();

    // assert statements
    assertEquals(total, subject.getTotal());
    assertEquals(total, subject.totalWithDiscount());
  }

  @Test
  public void WhenTotalIsMoreThanAThousand() {
    final Cart subject = new Cart();

    var products = buildProducts(5, 200);
    products.forEach(p -> subject.addProduct(p));

    var total = products.stream().mapToInt(p -> p.getPrice()).sum();
    var totalWithDiscount = total - (total * 5 / 100);
    // assert statements
    assertEquals(total, subject.getTotal());
    assertEquals(totalWithDiscount, subject.totalWithDiscount());
  }

  @Test
  public void WhenTotalIsMoreThanFiveThousands() {
    final Cart subject = new Cart();

    var products = buildProducts(5, 1000);
    products.forEach(p -> subject.addProduct(p));

    var total = products.stream().mapToInt(p -> p.getPrice()).sum();
    var totalWithDiscount = total - (total * 7 / 100);
    // assert statements
    assertEquals(total, subject.getTotal());
    assertEquals(totalWithDiscount, subject.totalWithDiscount());
  }

  @Test
  public void WhenTotalIsMoreThanTenThousands() {
    final Cart subject = new Cart();

    var products = buildProducts(5, 2000);
    products.forEach(p -> subject.addProduct(p));

    var total = products.stream().mapToInt(p -> p.getPrice()).sum();
    var totalWithDiscount = total - (total * 10 / 100);
    // assert statements
    assertEquals(total, subject.getTotal());
    assertEquals(totalWithDiscount, subject.totalWithDiscount());
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