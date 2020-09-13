package br.com.gporpino.apishoppingcart.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTests {

  @Test
  public void WhenHasNoDiscount() {

    final Product subject = new Product();
    subject.setPrice(10);

    // assert statements
    assertEquals(10, subject.getPrice());
    assertEquals(0, subject.getDiscount());
  }

  @Test
  public void WhenHasDiscount() {

    final Product subject = new Product();
    subject.setPrice(10);
    subject.setDiscount(10);

    // assert statements
    assertEquals(10, subject.getPrice());
    assertEquals(10, subject.getDiscount());
    assertEquals(9, subject.getSalePrice());
  }
}
