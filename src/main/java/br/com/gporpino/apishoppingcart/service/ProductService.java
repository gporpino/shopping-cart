package br.com.gporpino.apishoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gporpino.apishoppingcart.exceptions.ResourceNotFoundException;
import br.com.gporpino.apishoppingcart.model.Product;
import br.com.gporpino.apishoppingcart.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  ProductRepository repository;

  public Product findById(long id) {

    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No object found with this ID"));
  }

  public Product update(Product product) {
    Product entity = findById(product.getId());

    entity.setPrice(product.getPrice());
    entity.setDiscount(product.getDiscount());
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
