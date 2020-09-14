package br.com.gporpino.apishoppingcart.application.vo;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import br.com.gporpino.apishoppingcart.domain.entities.CartItem;
import br.com.gporpino.apishoppingcart.domain.entities.Coupon;

public class CartVO extends RepresentationModel<CartVO> implements Serializable {

  private static final long serialVersionUID = 1L;

  protected long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  private String name;
  private List<CartItem> items;
  private List<Coupon> coupons;
  private int subtotal;
  private int total;
  private int discount;

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
    CartVO other = (CartVO) obj;
    if (id != other.getId())
      return false;
    return true;
  }

}