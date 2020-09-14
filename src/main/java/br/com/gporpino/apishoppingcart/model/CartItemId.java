package br.com.gporpino.apishoppingcart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartItemId implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "cart_id")
  private long cartId;

  @Column(name = "product_id")
  private long productId;

  public long getCartId() {
    return cartId;
  }

  public void setCartId(long cartId) {
    this.cartId = cartId;
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

}
