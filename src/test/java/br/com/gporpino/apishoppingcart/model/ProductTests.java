package br.com.gporpino.apishoppingcart.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.gporpino.apishoppingcart.domain.entities.Product;

@SpringBootTest
public class ProductTests {

  @Test
  public void whenHasNoDiscount() {

    final Product subject = new Product();
    subject.setPrice(10);

    // assert statements
    assertEquals(10, subject.getPrice());
  }

  @Test
  public void whenHasDiscount() {

    final Product subject = new Product();
    subject.setPrice(10);

    // assert statements
    assertEquals(10, subject.getPrice());
  }
}
