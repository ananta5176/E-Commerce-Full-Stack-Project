package com.ecommerce.daos;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.entities.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

	List<Cart> findByCustomerId(int id);
	
	@Query(value="select * from cart where product_id=:p1 and customer_id=:p2",nativeQuery = true)
	Optional<Cart> findItemInCart(@Param("p1") int prodid,@Param("p2") int userid);
	
	@Modifying
	@Transactional
	@Query(value="delete from cart where customer_id=:p1",nativeQuery = true)
	void emptyCart(@Param("p1")int userid);
}
