package br.com.gporpino.apishoppingcart.domain.entities;

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

import br.com.gporpino.apishoppingcart.domain.enums.AmountDiscount;
import br.com.gporpino.apishoppingcart.domain.handler.NumberHandler;

@Entity
@Table
public class Cart implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> items = new ArrayList<CartItem>();
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<Coupon> coupons = new ArrayList<Coupon>();

  public Cart() {

  }

  public Cart(Long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<CartItem> getItems() {
    return items;
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

    var item = items.stream().filter(i -> i.getProduct().equals(product)).findFirst().orElse(null);
    if (item == null) {
      item = new CartItem(product);
      items.add(item);
    } else {
      item.increaseQuantity();
    }

    return product;
  }

  public Coupon addCoupon(Coupon coupon) {
    coupons.add(coupon);

    return coupon;
  }

  public int getSubtotal() {
    return items.stream().mapToInt((p) -> p.getSubtotal()).sum();
  }

  public int getTotal() {
    return getSubtotal() - (getSubtotal() * getDiscount() / 100);
  }

  public int getDiscount() {
    return amountDiscount() + couponDiscount();
  }

  private int amountDiscount() {

    if (NumberHandler.isBetween(getSubtotal(), AmountDiscount.START.from(), AmountDiscount.START.to())) {
      return AmountDiscount.START.discount();
    } else if (NumberHandler.isBetween(getSubtotal(), AmountDiscount.MEDIUM.from(), AmountDiscount.MEDIUM.to())) {
      return AmountDiscount.MEDIUM.discount();
    } else if (getSubtotal() >= AmountDiscount.EXTREME.from()) {
      return AmountDiscount.EXTREME.discount();
    }

    return 0;
  }

  private int couponDiscount() {
    return coupons.stream().mapToInt(c -> c.getDiscount()).max().orElse(0);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Cart other = (Cart) obj;
    if (id != other.id)
      return false;
    return true;
  }
}
