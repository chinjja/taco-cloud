package com.chinjja.taco.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.chinjja.taco.Order;
import com.chinjja.taco.User;

public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
