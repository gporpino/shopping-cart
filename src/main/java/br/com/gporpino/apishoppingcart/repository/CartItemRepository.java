package br.com.gporpino.apishoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gporpino.apishoppingcart.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
