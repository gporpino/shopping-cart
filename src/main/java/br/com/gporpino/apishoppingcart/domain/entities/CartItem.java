package br.com.gporpino.apishoppingcart.domain.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class CartItem implements Serializable {

  private static final long serialVersionUID = 1L;
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

  public int getSubtotal() {
    return (product.getPrice() - percent()) * getQuantity();
  }

  private int percent() {
    return product.getPrice() * discount() / 100;
  }

  private int discount() {
    if (getQuantity() >= 10) {
      return CartItem.DISCOUNT;
    }
    return CartItem.NO_DISCOUNT;
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
    CartItem other = (CartItem) obj;
    if (id != other.id)
      return false;
    return true;
  }

}