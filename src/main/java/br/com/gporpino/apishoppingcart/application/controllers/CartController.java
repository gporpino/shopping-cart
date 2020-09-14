package br.com.gporpino.apishoppingcart.application.controllers;

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

import br.com.gporpino.apishoppingcart.application.converter.Converter;
import br.com.gporpino.apishoppingcart.application.vo.CartVO;
import br.com.gporpino.apishoppingcart.domain.entities.Cart;
import br.com.gporpino.apishoppingcart.domain.services.interfaces.ICartService;

@RestController
@RequestMapping("/carts")
public class CartController {

  @Autowired
  private ICartService service;

  @GetMapping
  public List<CartVO> findAll() {
    return Converter.parse(service.findAll(), CartVO.class);
  }

  @GetMapping("/{id}")
  public CartVO findById(@PathVariable("id") Long id) {
    return Converter.parse(service.findById(id), CartVO.class);
  }

  @PostMapping
  public CartVO create(@RequestBody CartVO cart) {
    var entity = Converter.parse(cart, Cart.class);
    var vo = Converter.parse(service.create(entity), CartVO.class);
    return vo;
  }

  @PutMapping
  public CartVO update(@RequestBody CartVO cart) {
    var entity = Converter.parse(cart, Cart.class);
    var vo = Converter.parse(service.update(entity), CartVO.class);
    return vo;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}/add/product/{productId}")
  public CartVO addProduct(@PathVariable("id") Long id, @PathVariable("productId") Long productId) {
    return Converter.parse(service.addProduct(id, productId), CartVO.class);
  }

  @PutMapping("/{id}/add/coupon/{couponId}")
  public CartVO addCoupon(@PathVariable("id") Long id, @PathVariable("couponId") Long couponId) {
    return Converter.parse(service.addCoupon(id, couponId), CartVO.class);
  }

}
