package com.chinjja.taco.data;

import org.springframework.data.repository.CrudRepository;

import com.chinjja.taco.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
