package br.com.gporpino.apishoppingcart.domain.services.interfaces;

import br.com.gporpino.apishoppingcart.domain.entities.Cart;

public interface ICartService extends IService<Cart> {

  public Cart addProduct(Long id, Long productId);

  public Cart addCoupon(Long id, Long couponId);

}
