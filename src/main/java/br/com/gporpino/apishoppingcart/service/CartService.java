package br.com.gporpino.apishoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gporpino.apishoppingcart.exceptions.ResourceNotFoundException;
import br.com.gporpino.apishoppingcart.model.Cart;
import br.com.gporpino.apishoppingcart.repository.CartRepository;

@Service
public class CartService {

  @Autowired
  CartRepository repository;

  @Autowired
  ProductService productService;

  @Autowired
  CouponService couponService;

  public CartService() {
  }

  public CartService(CartRepository repository) {
    this.repository = repository;
  }

  public Cart findById(long id) {

    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No object found with this ID"));
  }

  public Cart update(Cart cart) {
    Cart entity = findById(cart.getId());

    entity.setName(cart.getName());

    return repository.save(entity);
  }

  public void delete(Long id) {
    Cart entity = findById(id);
    repository.delete(entity);
  }

  public Cart create(Cart cart) {
    return repository.save(cart);
  }

  public List<Cart> findAll() {
    return repository.findAll();
  }

  public Cart addProduct(Long id, Long productId) {
    var product = productService.findById(productId);
    var cart = findById(id);
    cart.addProduct(product);

    return repository.save(cart);
  }

  public Cart addCoupon(Long id, Long couponId) {
    var coupon = couponService.findById(couponId);
    var cart = findById(id);
    cart.addCoupon(coupon);

    return repository.save(cart);
  }

}
