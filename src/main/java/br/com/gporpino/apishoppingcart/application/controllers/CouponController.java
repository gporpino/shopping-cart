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
    var coupons = Converter.parse(service.findAll(), CouponVO.class);
    coupons.stream().forEach(p -> p.add(linkTo(methodOn(CouponController.class).findById(p.getId())).withSelfRel()));

    return coupons;
  }

  @GetMapping("/{id}")
  public CouponVO findById(@PathVariable("id") Long id) {
    var valueObject = Converter.parse(service.findById(id), CouponVO.class);
    valueObject.add(linkTo(methodOn(CouponController.class).findById(id)).withSelfRel());
    return valueObject;
  }

  @PostMapping
  public CouponVO create(@RequestBody CouponVO coupon) {
    var entity = Converter.parse(coupon, Coupon.class);
    var valueObject = Converter.parse(service.create(entity), CouponVO.class);

    valueObject.add(linkTo(methodOn(CouponController.class).findById(valueObject.getId())).withSelfRel());
    return valueObject;

  }

  @PutMapping
  public CouponVO update(@RequestBody CouponVO coupon) {
    var entity = Converter.parse(coupon, Coupon.class);
    var valueObject = Converter.parse(service.update(entity), CouponVO.class);
    valueObject.add(linkTo(methodOn(CouponController.class).findById(valueObject.getId())).withSelfRel());
    return valueObject;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseEntity.ok().build();
  }

}
