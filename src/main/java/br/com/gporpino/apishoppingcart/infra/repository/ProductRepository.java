package br.com.gporpino.apishoppingcart.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gporpino.apishoppingcart.domain.entities.Product;
import br.com.gporpino.apishoppingcart.domain.repository.IRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, IRepository<Product> {

}
