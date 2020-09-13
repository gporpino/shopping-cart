package br.com.gporpino.apishoppingcart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private int total;
  private int discount;
  private final List<Product> products = new ArrayList<Product>();

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getTotal() {
    return total;
  }

  public int getDiscount() {
    return discount;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public void setDiscount(int discount) {
    this.discount = discount;
  }

  public Product addProduct(Product product) {
    products.add(product);
    updateDiscounts();

    return product;
  }

  public int totalWithDiscount() {
    return getTotal() - (getTotal() * getDiscount() / 100);
  }

  private void updateDiscounts() {

    updateProductsDisconts();
    updateCartDiscounts();
  }

  private void updateCartDiscounts() {

    if (isBetween(total, 1000, 4999)) {
      setDiscount(5);
    } else if (isBetween(total, 5000, 9999)) {
      setDiscount(7);
    } else if (getTotal() >= 10000) {
      setDiscount(10);
    }
  }

  private void updateProductsDisconts() {
    if (products.size() >= 10) {
      products.forEach((p) -> p.setDiscount(10));
    }

    this.setTotal(products.stream().mapToInt((p) -> p.salePrice()).sum());
  }

  private static boolean isBetween(int x, int lower, int upper) {
    return lower <= x && x <= upper;
  }

}
