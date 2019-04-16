package com.beerhouse.repository;

import com.beerhouse.domain.model.Beer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BeerRepository extends CrudRepository<Beer, Integer> {

  Beer save(Beer beer);

  List<Beer> findAll();

  Optional<Beer> findById(Integer id);

  void delete(Integer id);
}
