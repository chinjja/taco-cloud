package com.chinjja.taco.data;

import org.springframework.data.repository.CrudRepository;

import com.chinjja.taco.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
