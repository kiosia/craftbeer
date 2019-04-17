package com.beerhouse.domain.model;

import com.beerhouse.domain.vo.BeerVo;

import javax.persistence.*;

@Entity
@Table(name = "beer")
public class Beer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column
  private String name;
  @Column
  private String ingredients;
  @Column(name = "alcohol_content")
  private String alcoholContent;
  @Column
  private Double price;
  @Column
  private String category;

  public Beer() {
    // Do nothing
  }

  private Beer(Builder builder) {
    id = builder.id;
    name = builder.name;
    ingredients = builder.ingredients;
    alcoholContent = builder.alcoholContent;
    price = builder.price;
    category = builder.category;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public static Builder beerBuilder() {
    return new Builder();
  }

  public void merge(BeerVo beerVo) {
    final Integer id = beerVo.getId();
    final String name = beerVo.getName();
    final String ingredients = beerVo.getIngredients();
    final String alcoholContent = beerVo.getAlcoholContent();
    final Double price = beerVo.getPrice();
    final String category = beerVo.getCategory();

    if (id != null) {
      this.id = id;
    }

    if (name != null) {
      this.name = name;
    }

    if (ingredients != null) {
      this.ingredients = ingredients;
    }

    if (alcoholContent != null) {
      this.alcoholContent = alcoholContent;
    }

    if (price != null) {
      this.price = price;
    }

    if (category != null) {
      this.category = category;
    }
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

    public Builder withId(Integer id) {
      this.id = id;
      return this;
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withIngredients(String ingredients) {
      this.ingredients = ingredients;
      return this;
    }

    public Builder withAlcoholContent(String alcoholContent) {
      this.alcoholContent = alcoholContent;
      return this;
    }

    public Builder withPrice(Double price) {
      this.price = price;
      return this;
    }

    public Builder withCategory(String category) {
      this.category = category;
      return this;
    }

    public Beer build() {
      return new Beer(this);
    }
  }
}
