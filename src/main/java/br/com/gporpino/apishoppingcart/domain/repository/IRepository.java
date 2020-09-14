package br.com.gporpino.apishoppingcart.domain.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
  Optional<T> findById(long id);

  T save(T t);

  void delete(T t);

  List<T> findAll();

}
