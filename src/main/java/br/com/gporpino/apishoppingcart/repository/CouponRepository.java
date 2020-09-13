package br.com.gporpino.apishoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gporpino.apishoppingcart.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
