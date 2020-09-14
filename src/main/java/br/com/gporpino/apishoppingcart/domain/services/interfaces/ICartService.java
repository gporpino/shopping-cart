package br.com.gporpino.apishoppingcart.domain.services.interfaces;

import java.util.List;

import br.com.gporpino.apishoppingcart.domain.entities.Cart;

public interface ICartService {

  public Cart findById(long id);

  public Cart update(Cart cart);

  public void delete(Long id);

  public Cart create(Cart cart);

  public List<Cart> findAll();

  public Cart addProduct(Long id, Long productId);

  public Cart addCoupon(Long id, Long couponId);

}
