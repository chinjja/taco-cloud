package com.chinjja.taco.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.chinjja.taco.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
