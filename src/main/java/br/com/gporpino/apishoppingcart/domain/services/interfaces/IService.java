package br.com.gporpino.apishoppingcart.domain.services.interfaces;

import java.util.List;

public interface IService<T> {

  public T findById(long id);

  public T update(T t);

  public void delete(Long id);

  public T create(T t);

  public List<T> findAll();

}
