package br.com.gporpino.apishoppingcart.application.controllers;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
    var coupons = Converter.parse(service.findAll(), CartVO.class);
    coupons.stream().forEach(p -> p.add(linkTo(methodOn(CartController.class).findById(p.getId())).withSelfRel()));

    return coupons;
  }

  @GetMapping("/{id}")
  public CartVO findById(@PathVariable("id") Long id) {
    var valueObject = Converter.parse(service.findById(id), CartVO.class);
    valueObject.add(linkTo(methodOn(CartController.class).findById(id)).withSelfRel());
    return valueObject;
  }

  @PostMapping
  public CartVO create(@RequestBody CartVO cart) {
    var entity = Converter.parse(cart, Cart.class);
    var valueObject = Converter.parse(service.create(entity), CartVO.class);

    valueObject.add(linkTo(methodOn(CartController.class).findById(valueObject.getId())).withSelfRel());
    return valueObject;

  }

  @PutMapping
  public CartVO update(@RequestBody CartVO cart) {
    var entity = Converter.parse(cart, Cart.class);
    var valueObject = Converter.parse(service.update(entity), CartVO.class);
    valueObject.add(linkTo(methodOn(CartController.class).findById(valueObject.getId())).withSelfRel());
    return valueObject;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}/add/product/{productId}")
  public CartVO addProduct(@PathVariable("id") Long id, @PathVariable("productId") Long productId) {
    var valueObject = Converter.parse(service.addProduct(id, productId), CartVO.class);
    valueObject.add(linkTo(methodOn(CartController.class).findById(valueObject.getId())).withSelfRel());
    return valueObject;
  }

  @PutMapping("/{id}/add/coupon/{couponId}")
  public CartVO addCoupon(@PathVariable("id") Long id, @PathVariable("couponId") Long couponId) {
    var valueObject = Converter.parse(service.addCoupon(id, couponId), CartVO.class);
    valueObject.add(linkTo(methodOn(CartController.class).findById(valueObject.getId())).withSelfRel());
    return valueObject;
  }

}
