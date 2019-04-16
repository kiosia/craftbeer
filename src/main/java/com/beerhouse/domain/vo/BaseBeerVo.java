package com.beerhouse.domain.vo;

import java.util.Optional;

public class BaseBeerVo {
  private String name;
  private String ingredients;
  private String alcoholContent;
  private Double price;
  private String category;

  public BaseBeerVo(Builder builder) {
    Optional.ofNullable(builder.name).ifPresent(this::setName);
    Optional.ofNullable(builder.ingredients).ifPresent(this::setIngredients);
    Optional.ofNullable(builder.alcoholContent).ifPresent(this::setAlcoholContent);
    Optional.ofNullable(builder.price).ifPresent(this::setPrice);
    Optional.ofNullable(builder.category).ifPresent(this::setCategory);
  }

  public BaseBeerVo() {
    // Do nothing
  }

  public static Builder<BaseBeerBuilder> baseBeerBuilder() {
    return new BaseBeerBuilder();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIngredients() {
    return ingredients;
  }

  public void setIngredients(String ingredients) {
    this.ingredients = ingredients;
  }

  public String getAlcoholContent() {
    return alcoholContent;
  }

  public void setAlcoholContent(String alcoholContent) {
    this.alcoholContent = alcoholContent;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public static class Builder<T> {

    protected Class<T> baseBeerVoBuilder;
    private String name;
    private String ingredients;
    private String alcoholContent;
    private Double price;
    private String category;

    private Builder() {
    }

    public Builder(Class<T> baseBeerVoBuilder) {
      this.baseBeerVoBuilder = baseBeerVoBuilder;
    }

    public T withName(String name) {
      this.name = name;
      return baseBeerVoBuilder.cast(this);
    }

    public T withIngredients(String ingredients) {
      this.ingredients = ingredients;
      return baseBeerVoBuilder.cast(this);
    }

    public T withAlcoholContent(String alcoholContent) {
      this.alcoholContent = alcoholContent;
      return baseBeerVoBuilder.cast(this);
    }

    public T withPrice(Double price) {
      this.price = price;
      return baseBeerVoBuilder.cast(this);
    }

    public T withCategory(String category) {
      this.category = category;
      return baseBeerVoBuilder.cast(this);
    }

    public BaseBeerVo build() {
      return new BaseBeerVo(this);
    }
  }

  public static final class BaseBeerBuilder extends Builder<BaseBeerBuilder> {

    public BaseBeerBuilder() {
      super(BaseBeerBuilder.class);
    }

  }
}
