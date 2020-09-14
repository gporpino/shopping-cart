package br.com.gporpino.apishoppingcart.domain.entities.interfaces;

public interface ICartItem {

  IProduct getProduct();

  int getQuantity();
}
