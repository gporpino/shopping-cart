package br.com.gporpino.apishoppingcart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gporpino.apishoppingcart.model.Cart;
import br.com.gporpino.apishoppingcart.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {

  @Autowired
  private CartService service;

  @GetMapping
  public List<Cart> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Cart findById(@PathVariable("id") Long id) {
    return service.findById(id);
  }

  @PostMapping
  public Cart create(@RequestBody Cart cart) {
    return service.create(cart);
  }

  @PutMapping
  public Cart update(@RequestBody Cart cart) {
    return service.update(cart);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}/add/product/{productId}")
  public Cart addProduct(@PathVariable("id") Long id, @PathVariable("productId") Long productId) {
    return service.addProduct(id, productId);
  }

  @PutMapping("/{id}/add/coupon/{couponId}")
  public Cart addCoupon(@PathVariable("id") Long id, @PathVariable("couponId") Long couponId) {
    return service.addCoupon(id, couponId);
  }

}
