package br.com.gporpino.apishoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gporpino.apishoppingcart.exceptions.ResourceNotFoundException;
import br.com.gporpino.apishoppingcart.model.CartItem;

import br.com.gporpino.apishoppingcart.repository.CartItemRepository;

@Service
public class CartItemService {

  @Autowired
  CartItemRepository repository;

  public CartItem findById(long id) {

    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No object found with this ID"));
  }

  public CartItem update(CartItem cartItem) {
    CartItem entity = findById(cartItem.getId());

    entity.setProduct(cartItem.getProduct());
    entity.setQuantity(cartItem.getQuantity());

    return repository.save(entity);
  }

  public void delete(Long id) {
    CartItem entity = findById(id);
    repository.delete(entity);
  }

  public CartItem create(CartItem cartItem) {
    return repository.save(cartItem);
  }

  public List<CartItem> findAll() {
    return repository.findAll();
  }
}
