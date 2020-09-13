package br.com.gporpino.apishoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private int price;
  private int discount;

  public long getId() {
    return this.id;
  }

  public int getPrice() {
    return price;
  }

  public int getDiscount() {
    return discount;
  }

  public void setId(final long id) {
    this.id = id;
  }

  public void setPrice(final int price) {
    this.price = price;
  }

  public void setDiscount(final int discount) {
    this.discount = discount;
  }

  public int salePrice() {
    return getPrice() - (getPrice() * getDiscount() / 100);
  }

}
