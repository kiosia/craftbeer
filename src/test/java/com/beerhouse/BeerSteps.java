package com.beerhouse;

import com.beerhouse.domain.vo.BaseBeerVo;
import com.beerhouse.domain.vo.BeerVo;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BeerSteps {

  private RestTemplate restTemplate;
  private ResponseEntity<List<BeerVo>> allBeersResponse;
  private Integer lastStatusCode;

  public BeerSteps() {
    restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
  }

  private String beersEndpoint() {
    return "http://localhost:9000/beerhouse/beers";
  }

  private ResponseEntity<BeerVo> getBeer(Integer id) {
    return restTemplate.getForEntity(beersEndpoint() + "/" + id, BeerVo.class);
  }

  private ResponseEntity<List<BeerVo>> getAllBeers() {
    return restTemplate.exchange(
        beersEndpoint(),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<BeerVo>>() {
        });
  }

  private void deleteBeer(Integer id) {
    restTemplate.delete(beersEndpoint() + "/" + id);
  }

  private Void patchBeer(Integer id) {
    return restTemplate.patchForObject(beersEndpoint() + "/" + id, beerRequest, Void.class);
  }

  private void putBeer(Integer id) {
    restTemplate.put(beersEndpoint() + "/" + id, beerRequest, BeerVo.class);
  }

  private ResponseEntity<BeerVo> postBeer() {
    return restTemplate.postForEntity(beersEndpoint(), beerRequest, BeerVo.class);
  }

  private ResponseEntity<BeerVo> beerResponse;
  private BaseBeerVo beerRequest;
  private List<Integer> beerIds = new ArrayList<>();
  private Integer lastAddedBeerId;

  @After
  public void clear() {
    if (beerIds != null) {
      beerIds.forEach(this::deleteBeer);
    }
    final ResponseEntity<List<BeerVo>> allBeers = getAllBeers();
    assertThat(allBeers.getBody().size()).isEqualTo(0);
  }

  @Given("the user wants to post a new Beer")
  public void the_user_wants_to_post_a_new_Beer() {
    beerRequest = new BaseBeerVo();
  }

  @Given("the request has the field name with value {string}")
  public void the_request_has_the_field_name_with_value(String name) {
    beerRequest.setName(name);
  }

  @Given("the request has the field ingredients with value {string}")
  public void the_request_has_the_field_ingredients_with_value(String ingredients) {
    beerRequest.setIngredients(ingredients);
  }

  @Given("the request has the field alcohol content with value {string}")
  public void the_request_has_the_field_alcohol_content_with_value(String alcoholContent) {
    beerRequest.setAlcoholContent(alcoholContent);
  }

  @Given("the request has the field price with value {double}")
  public void the_request_has_the_field_price_with_value(Double price) {
    beerRequest.setPrice(price);
  }

  @Given("the request has the field category with value {string}")
  public void the_request_has_the_field_category_with_value(String category) {
    beerRequest.setCategory(category);
  }

  @When("the user posts that Beer")
  public void the_user_posts_that_Beer() {
    beerResponse = postBeer();
    lastStatusCode = beerResponse.getStatusCodeValue();
    lastAddedBeerId = beerResponse.getBody().getId();
    beerIds.add(lastAddedBeerId);
  }

  @Then("the response should contain status code {int}")
  public void the_response_should_contain_status_code(Integer expectedStatusCode) {
    assertThat(lastStatusCode).isEqualTo(expectedStatusCode);
  }

  @Then("the response should contain a beer id")
  public void the_response_should_contain_a_beer_id() {
    assertThat(lastAddedBeerId).isNotNull();
  }

  @Then("the response should contain the field name with value {string}")
  public void the_response_should_contain_the_field_name_with_value(String expectedName) {
    assertThat(beerResponse.getBody().getName()).isEqualTo(expectedName);
  }

  @Then("the response should contain the field ingredients with value {string}")
  public void the_response_should_contain_the_field_ingredients_with_value(String expectedIngredients) {
    assertThat(beerResponse.getBody().getIngredients()).isEqualTo(expectedIngredients);
  }

  @Then("the response should contain the field alcohol content with value {string}")
  public void the_response_should_contain_the_field_alcohol_content_with_value(String expectedAlcoholContent) {
    assertThat(beerResponse.getBody().getAlcoholContent()).isEqualTo(expectedAlcoholContent);
  }

  @Then("the response should contain the field price with value {double}")
  public void the_response_should_contain_the_field_price_with_value(Double expectedPrice) {
    assertThat(beerResponse.getBody().getPrice()).isEqualTo(expectedPrice);
  }

  @Then("the response should contain the field category with value {string}")
  public void the_response_should_contain_the_field_category_with_value(String expectedCategory) {
    assertThat(beerResponse.getBody().getCategory()).isEqualTo(expectedCategory);
  }

  @When("the user wants to fetch that Beer")
  public void the_user_wants_to_fetch_that_Beer() {
    beerResponse = getBeer(lastAddedBeerId);
    lastStatusCode = beerResponse.getStatusCodeValue();
  }

  @Then("the user deletes the first one")
  public void the_user_deletes_the_first_one() {
    deleteBeer(beerIds.get(0));
    beerIds.remove(0);
  }

  @When("the user wants to fetch all Beers")
  public void the_user_wants_to_fetch_all_Beers() {
    allBeersResponse = getAllBeers();
    lastStatusCode = allBeersResponse.getStatusCodeValue();
  }

  @Then("the response should contain only one Beer with name {string}")
  public void the_response_should_contain_only_one_Beer_with_name(String expectedName) {
    assertThat(allBeersResponse.getBody().size()).isEqualTo(1);
    assertThat(allBeersResponse.getBody().get(0).getName()).isEqualTo(expectedName);
  }

  @Then("the response should contain a beer id on index {int}")
  public void the_response_should_contain_a_beer_id_on_index(Integer index) {
    assertThat(allBeersResponse.getBody().get(index).getId()).isNotNull();
  }

  @Then("the response should contain the field name with value {string} on index {int}")
  public void the_response_should_contain_the_field_name_with_value_on_index(String expectedName, Integer index) {
    assertThat(allBeersResponse.getBody().get(index).getName()).isEqualTo(expectedName);
  }

  @Then("the response should contain the field ingredients with value {string} on index {int}")
  public void the_response_should_contain_the_field_ingredients_with_value_on_index(String expectedIngredients, Integer index) {
    assertThat(allBeersResponse.getBody().get(index).getIngredients()).isEqualTo(expectedIngredients);
  }

  @Then("the response should contain the field alcohol content with value {string} on index {int}")
  public void the_response_should_contain_the_field_alcohol_content_with_value_on_index(String expectedAlcoholContent, Integer index) {
    assertThat(allBeersResponse.getBody().get(index).getAlcoholContent()).isEqualTo(expectedAlcoholContent);
  }

  @Then("the response should contain the field price with value {double} on index {int}")
  public void the_response_should_contain_the_field_price_with_value_on_index(Double expectedPrice, Integer index) {
    assertThat(allBeersResponse.getBody().get(index).getPrice()).isEqualTo(expectedPrice);
  }

  @Then("the response should contain the field category with value {string} on index {int}")
  public void the_response_should_contain_the_field_category_with_value_on_index(String expectedCategory, Integer index) {
    assertThat(allBeersResponse.getBody().get(index).getCategory()).isEqualTo(expectedCategory);
  }

  @Then("the user patches that Beer with a new price {double}")
  public void the_user_patches_that_Beer_with_a_new_price(Double price) {
    beerRequest.setPrice(price);
    patchBeer(lastAddedBeerId);
  }

  @Then("the user wants to put a new Beer on that id")
  public void the_user_wants_to_put_a_new_Beer_on_that_id() {
    beerRequest = new BaseBeerVo();
  }

  @Then("the user puts that Beer")
  public void the_user_puts_that_Beer() {
    putBeer(lastAddedBeerId);
  }

}
