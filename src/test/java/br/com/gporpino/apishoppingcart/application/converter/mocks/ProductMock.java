package br.com.gporpino.apishoppingcart.application.converter.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.gporpino.apishoppingcart.application.vo.ProductVO;
import br.com.gporpino.apishoppingcart.domain.entities.Product;

public class ProductMock {

  public Product mockEntity() {
    return mockEntity(0);
  }

  public ProductVO mockVO() {
    return mockVO(0);
  }

  public List<Product> mockEntityList() {
    List<Product> products = new ArrayList<Product>();
    for (int i = 0; i < 14; i++) {
      products.add(mockEntity(i));
    }
    return products;
  }

  public List<ProductVO> mockVOList() {
    List<ProductVO> products = new ArrayList<>();
    for (int i = 0; i < 14; i++) {
      products.add(mockVO(i));
    }
    return products;
  }

  private Product mockEntity(Integer number) {
    Product product = new Product();

    product.setName("First Name Test" + number);

    product.setId(number.longValue());
    product.setPrice(number);
    return product;
  }

  private ProductVO mockVO(Integer number) {
    ProductVO product = new ProductVO();
    product.setName("First Name Test" + number);

    product.setId(number.longValue());
    product.setPrice(number);
    return product;
  }

}
