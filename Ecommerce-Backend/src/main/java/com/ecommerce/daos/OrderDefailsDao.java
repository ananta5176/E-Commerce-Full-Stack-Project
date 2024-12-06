package com.ecommerce.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entities.Order;
import com.ecommerce.entities.OrderDetails;

@Repository
public interface OrderDefailsDao extends JpaRepository<OrderDetails, Integer> {
	List<OrderDetails> findByOrder(Order order);
}
