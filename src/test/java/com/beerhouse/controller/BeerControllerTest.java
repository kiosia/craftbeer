package com.beerhouse.controller;

import com.beerhouse.controller.error.NotFoundException;
import com.beerhouse.domain.vo.BeerResponseVo;
import com.beerhouse.domain.vo.BeerVo;
import com.beerhouse.domain.vo.MultipleBeersResponseVo;
import com.beerhouse.service.BeerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BeerControllerTest {

  private static final Integer BEER_ID = 1;

  @InjectMocks
  private BeerController beerController;

  @Mock
  private BeerService beerService;

  @Test
  public void whenIGetMultipleBeersItShouldCallTheService() {

    BeerVo beer1 = new BeerVo();
    beer1.setName("Test Beer");

    BeerVo beer2 = new BeerVo();
    beer2.setName("Test Beer 2");

    List<BeerVo> result = new ArrayList<>();
    result.add(beer1);
    result.add(beer2);

    when(beerService.findAll()).thenReturn(result);

    final MultipleBeersResponseVo beer = beerController.getBeers();

    assertEquals(200, beer.getStatusCodeValue());
    assertEquals("Test Beer", beer.getBody().get(0).getName());
    assertEquals("Test Beer 2", beer.getBody().get(1).getName());

    verify(beerService).findAll();
  }

  @Test
  public void whenIGetABeerItShouldCallTheService() {

    BeerVo result = new BeerVo();
    result.setName("Test Beer");

    when(beerService.findById(eq(BEER_ID))).thenReturn(result);

    final BeerResponseVo beer = beerController.getBeer(BEER_ID);

    assertEquals(200, beer.getStatusCodeValue());
    assertEquals("Test Beer", beer.getBody().getName());

    verify(beerService).findById(eq(BEER_ID));
  }

  @Test
  public void whenTheGetServiceThrowsANotFoundErrorItShouldFailGracefully() {

    NotFoundException notFoundException = new NotFoundException("Beer", BEER_ID);

    when(beerService.findById(eq(BEER_ID))).thenThrow(notFoundException);

    final BeerResponseVo beer = beerController.getBeer(BEER_ID);

    assertEquals(404, beer.getStatusCodeValue());

    verify(beerService).findById(eq(BEER_ID));
  }

  @Test
  public void whenIPatchABeerItShouldCallTheService() {

    BeerVo result = new BeerVo();
    result.setName("Test Beer");

    when(beerService.patch(eq(BEER_ID), any(BeerVo.class))).thenReturn(result);

    final BeerResponseVo beer = beerController.patchBeer(BEER_ID, new BeerVo());

    assertEquals(200, beer.getStatusCodeValue());
    assertEquals("Test Beer", beer.getBody().getName());

    verify(beerService).patch(eq(BEER_ID), any(BeerVo.class));
  }

  @Test
  public void whenThePatchServiceThrowsANotFoundErrorItShouldFailGracefully() {

    NotFoundException notFoundException = new NotFoundException("Beer", BEER_ID);

    when(beerService.patch(eq(BEER_ID), any(BeerVo.class))).thenThrow(notFoundException);

    final BeerResponseVo beer = beerController.patchBeer(BEER_ID, new BeerVo());

    assertEquals(404, beer.getStatusCodeValue());

    verify(beerService).patch(eq(BEER_ID), any(BeerVo.class));
  }

  @Test
  public void whenIPutABeerItShouldCallTheService() {

    BeerVo result = new BeerVo();
    result.setName("Test Beer");

    when(beerService.put(eq(BEER_ID), any(BeerVo.class))).thenReturn(result);

    final BeerResponseVo beer = beerController.putBeer(BEER_ID, new BeerVo());

    assertEquals(200, beer.getStatusCodeValue());
    assertEquals("Test Beer", beer.getBody().getName());

    verify(beerService).put(eq(BEER_ID), any(BeerVo.class));
  }

  @Test
  public void whenThePutServiceThrowsANotFoundErrorItShouldFailGracefully() {

    NotFoundException notFoundException = new NotFoundException("Beer", BEER_ID);

    when(beerService.put(eq(BEER_ID), any(BeerVo.class))).thenThrow(notFoundException);

    final BeerResponseVo beer = beerController.putBeer(BEER_ID, new BeerVo());

    assertEquals(404, beer.getStatusCodeValue());

    verify(beerService).put(eq(BEER_ID), any(BeerVo.class));
  }

  @Test
  public void whenIPostABeerItShouldCallTheService() {

    BeerVo result = new BeerVo();
    result.setName("Test Beer");

    when(beerService.save(any(BeerVo.class))).thenReturn(result);

    final BeerResponseVo beer = beerController.postBeer(new BeerVo());

    assertEquals(201, beer.getStatusCodeValue());
    assertEquals("Test Beer", beer.getBody().getName());

    verify(beerService).save(any(BeerVo.class));
  }

  @Test
  public void whenIDeleteABeerItShouldCallTheService() {

    BeerVo result = new BeerVo();
    result.setName("Test Beer");

    final BeerResponseVo beer = beerController.deleteBeer(BEER_ID);

    doNothing().when(beerService).delete(eq(BEER_ID));

    assertEquals(204, beer.getStatusCodeValue());

    verify(beerService).delete(eq(BEER_ID));
  }

  @Test
  public void whenTheDeleteServiceThrowsANotFoundErrorItShouldFailGracefully() {

    NotFoundException notFoundException = new NotFoundException("Beer", BEER_ID);

    doThrow(notFoundException).when(beerService).delete(eq(BEER_ID));

    final BeerResponseVo beer = beerController.deleteBeer(BEER_ID);

    assertEquals(404, beer.getStatusCodeValue());

    verify(beerService).delete(eq(BEER_ID));
  }
}
