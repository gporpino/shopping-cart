package br.com.gporpino.apishoppingcart.domain.entities.interfaces;

public interface IProduct extends IEntity<IProduct> {
  String getName();

  String getPrice();
}
