package br.com.gporpino.apishoppingcart.application.vo;

import org.springframework.hateoas.*;

import java.io.Serializable;

public class ProductVO extends RepresentationModel<ProductVO> implements Serializable {

  private static final long serialVersionUID = 1L;

  protected long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  private String name;
  private int price;

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(final int price) {
    this.price = price;
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
    ProductVO other = (ProductVO) obj;
    if (id != other.getId())
      return false;
    return true;
  }

}
