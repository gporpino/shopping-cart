package br.com.gporpino.apishoppingcart.domain.entities.interfaces;

import java.util.List;

public interface ICart extends IEntity<ICart> {

  String getName();

  List<ICartItem> getItems();

  List<ICoupon> getCoupons();

  int subtotal();

  int total();

  int discount();

}
