package com.chinjja.taco.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Component
@ConfigurationProperties("taco.orders")
@Data
@Validated
public class OrderProps {
	@Min(5)
	@Max(25)
	private int pageSize = 20;
}
