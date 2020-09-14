package br.com.gporpino.apishoppingcart.application.vo;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

public class CouponVO extends RepresentationModel<CouponVO> implements Serializable {

  private static final long serialVersionUID = 1L;

  protected long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  private String name;
  private int discount;

  public CouponVO() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
    CouponVO other = (CouponVO) obj;
    if (id != other.getId())
      return false;
    return true;
  }
}
