package br.com.gporpino.apishoppingcart.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class CartItem {

  private static final int DISCOUNT = 10;
  private static final int NO_DISCOUNT = 0;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  private Product product;

  private int quantity;

  public CartItem() {

  }

  public CartItem(Product product) {

    this.product = product;
    this.quantity = 1;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Object addProduct(Product p) {
    return null;
  }

  public void increaseQuantity() {
    setQuantity(getQuantity() + 1);
  }

  public int subtotal() {
    return product.getPrice() - (product.getPrice() * discount() / 100);
  }

  private int discount() {
    if (getQuantity() >= 10) {
      return CartItem.DISCOUNT;
    }
    return CartItem.NO_DISCOUNT;
  }

}