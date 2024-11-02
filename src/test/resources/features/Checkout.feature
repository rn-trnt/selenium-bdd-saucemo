@Checkout
Feature: Checkout functionality on SauceDemo

  Background:
    Given I am logged into SauceDemo with standard user

  @Positive
  Scenario: Successful checkout of a single product with valid information
    When I add a product to the checkout
    And I complete the checkout process with valid customer information "John", "Doe", "12345"
    Then I should see my product in the checkout overview
    And I finish the checkout process

  @Positive
  Scenario: Successful checkout of multiple products with valid information
    When I add multiple products to the checkout
    And I complete the checkout process with valid customer information "John", "Doe", "12345"
    Then I should see all my products in the checkout overview
    And I finish the checkout process

  @Negative
  Scenario Outline: Checkout with missing customer information
    When I add multiple products to the checkout
    And I leave "<information>" blank
    And I attempt to continue
    Then I should see an error message saying "<error>"

    Examples:
      | information  | error                           |
      | First Name   | Error: First Name is required   |
      | Last Name    | Error: Last Name is required    |
      | Postal Code  | Error: Postal Code is required  |

  @Positive
  Scenario: Close checkout error message
    When I add multiple products to the checkout
    And I attempt to continue with missing customer information
    Then I should be able to close the error message

  @Positive
  Scenario: Navigate to a product page from the Checkout Overview page
    When I am on the Checkout Overview page with a list of products
    And I click the product name
    Then I should be directed to the relevant product page.

  @Positive
  Scenario: Cancel checkout process
    When I am on the Checkout Overview page with a list of products
    And I click the Cancel button
    Then I should be redirected to the Inventory page

  @Positive
  Scenario: Redirected to Inventory Page after checkout
    When I complete the checkout process
    And I click the Back Home button
    Then I should be redirected to the Inventory page



