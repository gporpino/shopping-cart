package br.com.gporpino.apishoppingcart.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gporpino.apishoppingcart.domain.entities.Product;
import br.com.gporpino.apishoppingcart.domain.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService service;

  @GetMapping
  public List<Product> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Product findById(@PathVariable("id") Long id) {
    return service.findById(id);
  }

  @PostMapping
  public Product create(@RequestBody Product product) {
    return service.create(product);
  }

  @PutMapping
  public Product update(@RequestBody Product product) {
    return service.update(product);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseEntity.ok().build();
  }

}
