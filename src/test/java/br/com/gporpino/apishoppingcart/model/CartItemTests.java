package br.com.gporpino.apishoppingcart.model;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartItemTests {

  // @Test
  // public void whenHasAtLeastTenProducts() {
  // final CartProduct subject = new CartProduct();

  // var products = buildProducts(10);
  // products.forEach(p -> subject.addProduct(p));

  // // assert statements
  // // assertEquals(10, subject.getProducts().size());
  // assertEquals(90, subject.subtotal());
  // assertEquals(90, subject.total());
  // assertEquals(0, subject.discount());
  // }

  // @Test
  // public void whenHasLessThanTenProducts() {
  // final Cart subject = new Cart();

  // var products = buildProducts(9);
  // products.forEach(p -> subject.addProduct(p));

  // // assert statements
  // // assertEquals(9, subject.getProducts().size());
  // assertEquals(90, subject.subtotal());
  // assertEquals(90, subject.total());
  // }

  // // private methods

  // private List<Product> buildProducts(final int size, int price) {
  // List<Product> products = new ArrayList<Product>();

  // IntStream.range(0, size).forEach(i -> {
  // final Product product = new Product();
  // product.setPrice(price);
  // products.add(product);
  // });

  // return products;
  // }

  // private List<Product> buildProducts(final int size) {
  // return buildProducts(size, 10);
  // }
}
