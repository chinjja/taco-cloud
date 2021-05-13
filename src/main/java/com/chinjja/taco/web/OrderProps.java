package com.chinjja.taco.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("taco.orders")
@Data
public class OrderProps {
	private int pageSize = 20;
}
