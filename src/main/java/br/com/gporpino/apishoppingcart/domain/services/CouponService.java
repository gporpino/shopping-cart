package br.com.gporpino.apishoppingcart.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gporpino.apishoppingcart.application.exceptions.ResourceNotFoundException;
import br.com.gporpino.apishoppingcart.domain.entities.Coupon;
import br.com.gporpino.apishoppingcart.domain.repository.IRepository;
import br.com.gporpino.apishoppingcart.domain.services.interfaces.ICouponService;

@Service
public class CouponService implements ICouponService {

  @Autowired
  IRepository<Coupon> repository;

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
