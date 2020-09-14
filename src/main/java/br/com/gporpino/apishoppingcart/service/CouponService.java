package br.com.gporpino.apishoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gporpino.apishoppingcart.exceptions.ResourceNotFoundException;
import br.com.gporpino.apishoppingcart.model.Coupon;
import br.com.gporpino.apishoppingcart.repository.CouponRepository;

@Service
public class CouponService {

  @Autowired
  CouponRepository repository;

  public Coupon findById(long id) {

    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No object found with this ID"));
  }

  public Coupon update(Coupon coupon) {
    Coupon entity = findById(coupon.getId());

    entity.setName(coupon.getName());
    entity.setDiscount(coupon.getDiscount());
    return repository.save(entity);
  }

  public void delete(Long id) {
    Coupon entity = findById(id);
    repository.delete(entity);
  }

  public Coupon create(Coupon coupon) {
    return repository.save(coupon);
  }

  public List<Coupon> findAll() {
    return repository.findAll();
  }
}
