package br.com.gporpino.apishoppingcart.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gporpino.apishoppingcart.domain.entities.Product;
import br.com.gporpino.apishoppingcart.application.exceptions.ResourceNotFoundException;
import br.com.gporpino.apishoppingcart.infra.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  ProductRepository repository;

  public Product findById(long id) {

    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No object found with this ID"));
  }

  public Product update(Product product) {
    Product entity = findById(product.getId());

    entity.setName(product.getName());
    entity.setPrice(product.getPrice());

    return repository.save(entity);
  }

  public void delete(Long id) {
    Product entity = findById(id);
    repository.delete(entity);
  }

  public Product create(Product product) {
    return repository.save(product);
  }

  public List<Product> findAll() {
    return repository.findAll();
  }
}
