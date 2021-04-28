package com.chinjja.taco.data;

import com.chinjja.taco.Order;

public interface OrderRepository {
	Order save(Order order);
}
