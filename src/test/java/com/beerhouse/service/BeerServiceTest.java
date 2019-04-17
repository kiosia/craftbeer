package com.beerhouse.service;

import com.beerhouse.controller.error.NotFoundException;
import com.beerhouse.domain.model.Beer;
import com.beerhouse.domain.vo.BaseBeerVo;
import com.beerhouse.domain.vo.BeerVo;
import com.beerhouse.repository.BeerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BeerServiceTest {

  private static final Integer BEER_ID = 1;

  @InjectMocks
  private BeerService beerService;

  @Mock
  private BeerRepository beerRepository;

  @Test
  public void whenICallFindAllItShouldCallTheRepositoryAndMapTheResultToVo() {
    List<Beer> repositoryResult = new ArrayList<>();

    when(beerRepository.findAll()).thenReturn(repositoryResult);

    final List<BeerVo> result = beerService.findAll();

    assertEquals(repositoryResult.size(), result.size());

    verify(beerRepository).findAll();
  }

  @Test
  public void whenICallFindByIdItShouldCallTheRepositoryAndMapTheResultToVo() {
    Beer beer = new Beer();
    beer.setName("Beer Name");
    Optional<Beer> repositoryResult = Optional.of(beer);

    when(beerRepository.findById(eq(BEER_ID))).thenReturn(repositoryResult);

    final BeerVo result = beerService.findById(BEER_ID);

    assertEquals("Beer Name", result.getName());

    verify(beerRepository).findById(eq(BEER_ID));
  }

  @Test(expected = NotFoundException.class)
  public void whenTheFindByIdRepositoryReturnsEmptyItShouldReplicateThrowError() {
    when(beerRepository.findById(eq(BEER_ID))).thenReturn(Optional.empty());

    beerService.findById(BEER_ID);
  }

  @Test
  public void whenICallPatchItShouldCallTheRepositoryAndMapTheResultToVo() {
    Beer beer = new Beer();
    beer.setName("Beer Name");
    Optional<Beer> repositoryResult = Optional.of(beer);

    when(beerRepository.findById(eq(BEER_ID))).thenReturn(repositoryResult);
    when(beerRepository.save(any(Beer.class))).thenReturn(beer);

    final BeerVo result = beerService.patch(BEER_ID, new BeerVo());

    assertEquals("Beer Name", result.getName());

    verify(beerRepository).findById(eq(BEER_ID));
    verify(beerRepository).save(any(Beer.class));
  }

  @Test(expected = NotFoundException.class)
  public void whenThePatchRepositoryReturnsEmptyItShouldReplicateThrowError() {
    when(beerRepository.findById(eq(BEER_ID))).thenReturn(Optional.empty());

    beerService.patch(BEER_ID, new BeerVo());
  }

  @Test
  public void whenICallPutItShouldCallTheRepositoryAndMapTheResultToVo() {
    Beer beer = new Beer();
    beer.setName("Beer Name");
    Optional<Beer> repositoryResult = Optional.of(beer);

    when(beerRepository.findById(eq(BEER_ID))).thenReturn(repositoryResult);
    when(beerRepository.save(any(Beer.class))).thenReturn(beer);

    final BeerVo result = beerService.put(BEER_ID, new BeerVo());

    assertEquals("Beer Name", result.getName());

    verify(beerRepository).findById(eq(BEER_ID));
    verify(beerRepository).save(any(Beer.class));
  }

  @Test(expected = NotFoundException.class)
  public void whenThePutRepositoryReturnsEmptyItShouldReplicateThrowError() {
    when(beerRepository.findById(eq(BEER_ID))).thenReturn(Optional.empty());

    beerService.put(BEER_ID, new BeerVo());
  }

  @Test
  public void whenICallSaveItShouldCallTheRepositoryAndMapTheResultToVo() {
    Beer beer = new Beer();
    beer.setName("Test Beer");

    when(beerRepository.save(any(Beer.class))).thenReturn(beer);

    final BeerVo result = beerService.save(new BaseBeerVo());

    assertEquals("Test Beer", result.getName());

    verify(beerRepository).save(any(Beer.class));
  }

  @Test
  public void whenICallDeleteItShouldCallTheRepository() {
    Beer beer = new Beer();
    beer.setName("Beer Name");
    Optional<Beer> repositoryResult = Optional.of(beer);

    when(beerRepository.findById(eq(BEER_ID))).thenReturn(repositoryResult);

    beerService.delete(BEER_ID);

    verify(beerRepository).findById(eq(BEER_ID));
    verify(beerRepository).delete(eq(BEER_ID));
  }

  @Test(expected = NotFoundException.class)
  public void whenTheDeleteRepositoryReturnsEmptyItShouldReplicateThrowError() {
    when(beerRepository.findById(eq(BEER_ID))).thenReturn(Optional.empty());

    beerService.delete(BEER_ID);
  }
}
