package br.com.gporpino.apishoppingcart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Cart implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<Product> products = new ArrayList<Product>();
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<Coupon> coupons = new ArrayList<Coupon>();

  public Cart() {

  }

  public Cart(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Product> getProducts() {
    return products;
  }

  public List<Coupon> getCoupons() {
    return coupons;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Product addProduct(Product product) {
    products.add(product);
    updateDiscounts();

    return product;
  }

  public Coupon addCoupon(Coupon coupon) {
    coupons.add(coupon);
    updateDiscounts();

    return coupon;
  }

  public int subtotal() {
    return products.stream().mapToInt((p) -> p.salePrice()).sum();
  }

  public int total() {
    return subtotal() - (subtotal() * discount() / 100);
  }

  public int discount() {
    return amountDiscount() + couponDiscount();
  }

  public int amountDiscount() {

    if (isBetween(subtotal(), 1000, 4999)) {
      return 5;
    } else if (isBetween(subtotal(), 5000, 9999)) {
      return 7;
    } else if (subtotal() >= 10000) {
      return 10;
    }

    return 0;
  }

  public int couponDiscount() {
    return coupons.stream().mapToInt(c -> c.getDiscount()).max().orElse(0);
  }

  private void updateDiscounts() {
    updateProductsDisconts();
  }

  private void updateProductsDisconts() {
    if (products.size() >= 10) {
      products.forEach((p) -> p.setDiscount(10));
    }
  }

  private static boolean isBetween(int x, int lower, int upper) {
    return lower <= x && x <= upper;
  }

}
