package br.com.gporpino.apishoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gporpino.apishoppingcart.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
