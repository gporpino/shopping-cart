package br.com.gporpino.apishoppingcart.application.vo;

import java.io.Serializable;

public class ProductVO extends BaseVO implements Serializable {

  private static final long serialVersionUID = 1L;

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

}
