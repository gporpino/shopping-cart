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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import br.com.gporpino.apishoppingcart.application.converter.Converter;
import br.com.gporpino.apishoppingcart.application.vo.ProductVO;
import br.com.gporpino.apishoppingcart.domain.entities.Product;
import br.com.gporpino.apishoppingcart.domain.services.interfaces.IProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private IProductService service;

  @GetMapping
  public List<ProductVO> findAll() {
    var products = Converter.parse(service.findAll(), ProductVO.class);
    products.stream().forEach(p -> p.add(linkTo(methodOn(ProductController.class).findById(p.getId())).withSelfRel()));

    return products;
  }

  @GetMapping("/{id}")
  public ProductVO findById(@PathVariable("id") Long id) {
    var productVO = Converter.parse(service.findById(id), ProductVO.class);
    productVO.add(linkTo(methodOn(ProductController.class).findById(id)).withSelfRel());
    return productVO;
  }

  @PostMapping
  public ProductVO create(@RequestBody ProductVO product) {
    var entity = Converter.parse(product, Product.class);
    var productVO = Converter.parse(service.create(entity), ProductVO.class);

    productVO.add(linkTo(methodOn(ProductController.class).findById(productVO.getId())).withSelfRel());
    return productVO;

  }

  @PutMapping
  public ProductVO update(@RequestBody ProductVO product) {
    var entity = Converter.parse(product, Product.class);
    var productVO = Converter.parse(service.update(entity), ProductVO.class);
    productVO.add(linkTo(methodOn(ProductController.class).findById(productVO.getId())).withSelfRel());
    return productVO;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseEntity.ok().build();
  }

}
