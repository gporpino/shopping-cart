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
import br.com.gporpino.apishoppingcart.application.vo.CouponVO;
import br.com.gporpino.apishoppingcart.domain.entities.Coupon;
import br.com.gporpino.apishoppingcart.domain.services.interfaces.ICouponService;

@RestController
@RequestMapping("/coupons")
public class CouponController {

  @Autowired
  private ICouponService service;

  @GetMapping
  public List<CouponVO> findAll() {
    return Converter.parse(service.findAll(), CouponVO.class);
  }

  @GetMapping("/{id}")
  public CouponVO findById(@PathVariable("id") Long id) {
    return Converter.parse(service.findById(id), CouponVO.class);
  }

  @PostMapping
  public CouponVO create(@RequestBody CouponVO product) {
    var entity = Converter.parse(product, Coupon.class);
    var vo = Converter.parse(service.create(entity), CouponVO.class);
    return vo;
  }

  @PutMapping
  public CouponVO update(@RequestBody CouponVO product) {
    var entity = Converter.parse(product, Coupon.class);
    var vo = Converter.parse(service.update(entity), CouponVO.class);
    return vo;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseEntity.ok().build();
  }

}
