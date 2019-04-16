package com.beerhouse.controller.error;

public class NotFoundException extends RuntimeException {

  private final String resourceName;
  private final Integer id;

  public NotFoundException(String resourceName, Integer id) {
    this.resourceName = resourceName;
    this.id = id;
  }

}
