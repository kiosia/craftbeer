package com.beerhouse.domain.vo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BeerResponseVo extends ResponseEntity<BeerVo> {
  public BeerResponseVo(HttpStatus status) {
    super(status);
  }

  public BeerResponseVo(BeerVo body, HttpStatus status) {
    super(body, status);
  }
}
