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

import br.com.gporpino.apishoppingcart.handler.NumberHandler;
import br.com.gporpino.apishoppingcart.model.enums.AmountDiscount;

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

    if (NumberHandler.isBetween(subtotal(), AmountDiscount.START.from(), AmountDiscount.START.to())) {
      return AmountDiscount.START.discount();
    } else if (NumberHandler.isBetween(subtotal(), AmountDiscount.MEDIUM.from(), AmountDiscount.MEDIUM.to())) {
      return AmountDiscount.MEDIUM.discount();
    } else if (subtotal() >= AmountDiscount.EXTREME.from()) {
      return AmountDiscount.EXTREME.discount();
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

}
