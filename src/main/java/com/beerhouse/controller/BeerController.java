package com.beerhouse.controller;

import com.beerhouse.controller.error.NotFoundException;
import com.beerhouse.domain.vo.BaseBeerVo;
import com.beerhouse.domain.vo.BeerVo;
import com.beerhouse.domain.vo.MultipleBeersResponseVo;
import com.beerhouse.domain.vo.BeerResponseVo;
import com.beerhouse.service.BeerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Api(value = "CraftBeer")
@RequestMapping("/beers")
public class BeerController {

  @Autowired
  private BeerService beerService;

  @GetMapping(value = "")
  @ApiOperation(
      value = "Fetch all Beers",
      tags = {"Beers"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation")
  })
  public MultipleBeersResponseVo getBeers() {
    return new MultipleBeersResponseVo(beerService.findAll(), HttpStatus.OK);
  }


  @GetMapping(value = "/{beer_id}")
  @ApiOperation(
      value = "Fetch a Beer given its Id",
      tags = {"Beers"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 404, message = "Not found")
  })
  public BeerResponseVo getBeer(
      @NotNull @ApiParam(value = "Beer Id")
      @PathVariable("beer_id") Integer id) {
    try {
      return new BeerResponseVo(beerService.findById(id), HttpStatus.OK);
    } catch (NotFoundException e) {
      return new BeerResponseVo(HttpStatus.NOT_FOUND);
    }
  }

  @PatchMapping(value = "/{beer_id}")
  @ApiOperation(
      value = "Patch a Beer given its Id and the desired fields to be patched on the body",
      tags = {"Beers"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 404, message = "Not found")
  })
  public BeerResponseVo patchBeer(
      @NotNull @ApiParam(value = "Beer Id")
      @PathVariable("beer_id") final Integer id,
      @NotNull @ApiParam(value = "Request body")
      @Validated @RequestBody BeerVo beerVo) {
    try {
      return new BeerResponseVo(beerService.patch(id, beerVo), HttpStatus.OK);
    } catch (NotFoundException e) {
      return new BeerResponseVo(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping(value = "/{beer_id}")
  @ApiOperation(
      value = "Replaces a Beer given its Id and the new resource on the body",
      tags = {"Beers"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 404, message = "Not found")
  })
  public BeerResponseVo putBeer(
      @NotNull @ApiParam(value = "Beer Id")
      @PathVariable("beer_id") final Integer id,
      @NotNull @ApiParam(value = "Request body")
      @Validated @RequestBody BeerVo beerVo) {
    try {
      return new BeerResponseVo(beerService.put(id, beerVo), HttpStatus.OK);
    } catch (NotFoundException e) {
      return new BeerResponseVo(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(value = "")
  @ApiOperation(
      value = "Create a Beer with the data on the body",
      tags = {"Beers"})
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Created")
  })
  public BeerResponseVo postBeer(
      @NotNull @ApiParam(value = "Request body")
      @Validated @RequestBody BaseBeerVo beerVo) {
    return new BeerResponseVo(beerService.save(beerVo), HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/{beer_id}")
  @ApiOperation(
      value = "Delete a Beer",
      tags = {"Beers"})
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "No content"),
      @ApiResponse(code = 404, message = "Not found")
  })
  public BeerResponseVo deleteBeer(
      @NotNull @ApiParam(value = "Beer Id")
      @PathVariable("beer_id") final Integer id) {
    try {
      beerService.delete(id);
      return new BeerResponseVo(HttpStatus.NO_CONTENT);
    } catch (NotFoundException e) {
      return new BeerResponseVo(HttpStatus.NOT_FOUND);
    }
  }
}
