package br.com.gporpino.apishoppingcart.application.vo;

import java.io.Serializable;
import java.util.List;

import br.com.gporpino.apishoppingcart.domain.entities.CartItem;
import br.com.gporpino.apishoppingcart.domain.entities.Coupon;

public class CartVO extends BaseVO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String name;
  private List<CartItem> items;
  private List<Coupon> coupons;
  private int subtotal;
  private int total;
  private int discount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<CartItem> getItems() {
    return items;
  }

  public void setItems(List<CartItem> items) {
    this.items = items;
  }

  public List<Coupon> getCoupons() {
    return coupons;
  }

  public void setCoupons(List<Coupon> coupons) {
    this.coupons = coupons;
  }

  public int getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(int subtotal) {
    this.subtotal = subtotal;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getDiscount() {
    return discount;
  }

  public void setDiscount(int discount) {
    this.discount = discount;
  }

}