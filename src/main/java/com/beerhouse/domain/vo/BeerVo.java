package com.beerhouse.domain.vo;

import java.util.Optional;

public class BeerVo extends BaseBeerVo {
  private Integer id;

  private BeerVo(BeerVo.Builder builder) {
    Optional.ofNullable(builder.id).ifPresent(this::setId);
    Optional.ofNullable(builder.name).ifPresent(this::setName);
    Optional.ofNullable(builder.ingredients).ifPresent(this::setIngredients);
    Optional.ofNullable(builder.alcoholContent).ifPresent(this::setAlcoholContent);
    Optional.ofNullable(builder.price).ifPresent(this::setPrice);
    Optional.ofNullable(builder.category).ifPresent(this::setCategory);
  }

  public BeerVo() {
    // Do nothing
  }

  public static BeerVo.Builder beerBuilder() {
    return new BeerVo.Builder();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public static final class Builder {
    private Integer id;
    private String name;
    private String ingredients;
    private String alcoholContent;
    private Double price;
    private String category;

    private Builder() {
    }

    public BeerVo.Builder withId(Integer id) {
      this.id = id;
      return this;
    }

    public BeerVo.Builder withName(String name) {
      this.name = name;
      return this;
    }

    public BeerVo.Builder withIngredients(String ingredients) {
      this.ingredients = ingredients;
      return this;
    }

    public BeerVo.Builder withAlcoholContent(String alcoholContent) {
      this.alcoholContent = alcoholContent;
      return this;
    }

    public BeerVo.Builder withPrice(Double price) {
      this.price = price;
      return this;
    }

    public BeerVo.Builder withCategory(String category) {
      this.category = category;
      return this;
    }

    public BeerVo build() {
      return new BeerVo(this);
    }
  }
}
