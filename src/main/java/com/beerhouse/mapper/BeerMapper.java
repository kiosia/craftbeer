package com.beerhouse.mapper;

import com.beerhouse.domain.model.Beer;
import com.beerhouse.domain.vo.BaseBeerVo;
import com.beerhouse.domain.vo.BeerVo;

public class BeerMapper {
  public static BeerVo mapDomainToVo(Beer beer) {
    return BeerVo.newBuilder()
        .withId(beer.getId())
        .withName(beer.getName())
        .withIngredients(beer.getIngredients())
        .withAlcoholContent(beer.getAlcoholContent())
        .withPrice(beer.getPrice())
        .withCategory(beer.getCategory())
        .build();
  }

  public static Beer mapVoToDomain(BeerVo beerVo) {
    return Beer.newBuilder()
        .withId(beerVo.getId())
        .withName(beerVo.getName())
        .withIngredients(beerVo.getIngredients())
        .withAlcoholContent(beerVo.getAlcoholContent())
        .withPrice(beerVo.getPrice())
        .withCategory(beerVo.getCategory())
        .build();
  }

  public static Beer mapBaseBeerVoToDomain(BaseBeerVo baseBeerVo) {
    return Beer.newBuilder()
        .withName(baseBeerVo.getName())
        .withIngredients(baseBeerVo.getIngredients())
        .withAlcoholContent(baseBeerVo.getAlcoholContent())
        .withPrice(baseBeerVo.getPrice())
        .withCategory(baseBeerVo.getCategory())
        .build();
  }
}
