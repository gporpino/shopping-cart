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

import br.com.gporpino.apishoppingcart.domain.entities.Coupon;
import br.com.gporpino.apishoppingcart.domain.services.CouponService;

@RestController
@RequestMapping("/coupons")
public class CouponController {

  @Autowired
  private CouponService service;

  @GetMapping
  public List<Coupon> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Coupon findById(@PathVariable("id") Long id) {
    return service.findById(id);
  }

  @PostMapping
  public Coupon create(@RequestBody Coupon cart) {
    return service.create(cart);
  }

  @PutMapping
  public Coupon update(@RequestBody Coupon cart) {
    return service.update(cart);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseEntity.ok().build();
  }

}
