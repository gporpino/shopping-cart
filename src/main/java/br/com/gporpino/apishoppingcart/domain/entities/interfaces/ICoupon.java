package br.com.gporpino.apishoppingcart.domain.entities.interfaces;

public interface ICoupon extends IEntity<ICoupon> {
  String getName();

  String getDiscount();
}
