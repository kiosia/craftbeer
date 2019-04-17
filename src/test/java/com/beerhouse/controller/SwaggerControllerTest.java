package com.beerhouse.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SwaggerControllerTest {

  @InjectMocks
  SwaggerController swaggerController;

  @Test
  public void whenICallSwaggerControllerItShouldReturnTheSwaggerUrl() {
    assertEquals("redirect:swagger-ui.html", swaggerController.index());
  }

}
