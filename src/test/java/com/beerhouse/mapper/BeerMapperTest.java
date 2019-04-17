package com.beerhouse.mapper;

import com.beerhouse.domain.model.Beer;
import com.beerhouse.domain.vo.BaseBeerVo;
import com.beerhouse.domain.vo.BeerVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BeerMapperTest {

  private static final Integer BEER_ID = 1;
  private static final String BEER_NAME = "Beer Name";
  private static final String BEER_INGREDIENTS = "Beer Ingredients";
  private static final String BEER_ALCOHOL = "Beer Alcohol Content";
  private static final Double BEER_PRICE = 19.99;
  private static final String BEER_CATEGORY = "Beer Category";

  @Test
  public void whenIWantToMapTheDomainToVoItShouldBeMappedCorrectly() {
    Beer beer = Beer.beerBuilder()
        .withId(BEER_ID)
        .withName(BEER_NAME)
        .withIngredients(BEER_INGREDIENTS)
        .withAlcoholContent(BEER_ALCOHOL)
        .withPrice(BEER_PRICE)
        .withCategory(BEER_CATEGORY)
        .build();

    final BeerVo beerVo = BeerMapper.mapDomainToVo(beer);

    assertEquals(BEER_ID, beerVo.getId());
    assertEquals(BEER_NAME, beerVo.getName());
    assertEquals(BEER_INGREDIENTS, beerVo.getIngredients());
    assertEquals(BEER_ALCOHOL, beerVo.getAlcoholContent());
    assertEquals(BEER_PRICE, beerVo.getPrice());
    assertEquals(BEER_CATEGORY, beerVo.getCategory());
  }

  @Test
  public void whenIWantToMapTheBaseBeerVoToDomainItShouldBeMappedCorrectly() {
    BaseBeerVo baseBeerVo = BaseBeerVo.baseBeerBuilder()
        .withName(BEER_NAME)
        .withIngredients(BEER_INGREDIENTS)
        .withAlcoholContent(BEER_ALCOHOL)
        .withPrice(BEER_PRICE)
        .withCategory(BEER_CATEGORY)
        .build();

    final Beer beer = BeerMapper.mapBaseBeerVoToDomain(baseBeerVo);

    assertEquals(BEER_NAME, beer.getName());
    assertEquals(BEER_INGREDIENTS, beer.getIngredients());
    assertEquals(BEER_ALCOHOL, beer.getAlcoholContent());
    assertEquals(BEER_PRICE, beer.getPrice());
    assertEquals(BEER_CATEGORY, beer.getCategory());
  }

  @Test
  public void whenIWantToMapTheVoToDomainItShouldBeMappedCorrectly() {
    BeerVo beerVo = BeerVo.beerBuilder()
        .withId(BEER_ID)
        .withName(BEER_NAME)
        .withIngredients(BEER_INGREDIENTS)
        .withAlcoholContent(BEER_ALCOHOL)
        .withPrice(BEER_PRICE)
        .withCategory(BEER_CATEGORY)
        .build();

    final Beer beer = BeerMapper.mapVoToDomain(beerVo);

    assertEquals(BEER_ID, beer.getId());
    assertEquals(BEER_NAME, beer.getName());
    assertEquals(BEER_INGREDIENTS, beer.getIngredients());
    assertEquals(BEER_ALCOHOL, beer.getAlcoholContent());
    assertEquals(BEER_PRICE, beer.getPrice());
    assertEquals(BEER_CATEGORY, beer.getCategory());
  }
}
