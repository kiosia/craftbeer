package com.beerhouse.service;

import com.beerhouse.controller.error.NotFoundException;
import com.beerhouse.domain.model.Beer;
import com.beerhouse.domain.vo.BaseBeerVo;
import com.beerhouse.domain.vo.BeerVo;
import com.beerhouse.mapper.BeerMapper;
import com.beerhouse.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class BeerService {

  @Autowired
  BeerRepository beerRepository;

  public List<BeerVo> findAll() {
    return beerRepository.findAll().stream().map(BeerMapper::mapDomainToVo).collect(toList());
  }

  public BeerVo findById(Integer id) {
    Beer beer = beerRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Beer", id));
    return BeerMapper.mapDomainToVo(beer);
  }

  public BeerVo patch(Integer id, BeerVo beerVo) {
    final Beer beer = beerRepository.findById(id).orElseThrow(() -> new NotFoundException("Beer", id));
    beer.merge(beerVo);
    return BeerMapper.mapDomainToVo(beerRepository.save(beer));
  }

  public BeerVo put(Integer id, BeerVo beerVo) {
    beerRepository.findById(id).orElseThrow(() -> new NotFoundException("Beer", id));
    beerVo.setId(id);
    return BeerMapper.mapDomainToVo(beerRepository.save(BeerMapper.mapVoToDomain(beerVo)));
  }

  public BeerVo save(BaseBeerVo beerVo) {
    return BeerMapper.mapDomainToVo(beerRepository.save(BeerMapper.mapBaseBeerVoToDomain(beerVo)));
  }

  public void delete(Integer id) {
    beerRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Beer", id));
    beerRepository.delete(id);
  }
}
