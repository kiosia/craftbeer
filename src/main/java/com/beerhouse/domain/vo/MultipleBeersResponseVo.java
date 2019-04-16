package com.beerhouse.domain.vo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class MultipleBeersResponseVo extends ResponseEntity<List<BeerVo>> {
  public MultipleBeersResponseVo(List<BeerVo> body, HttpStatus status) {
    super(body, status);
  }
}
