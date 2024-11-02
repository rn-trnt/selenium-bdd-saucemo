@Cart
Feature: Shopping Cart functionality on SauceDemo

  Background:
    Given I am logged in with standard user

  @Positive
  Scenario Outline: Navigate to shopping cart page
    When I click on the shopping cart icon from "<page>" page
    Then I should be redirected to the shopping cart page

    Examples:
      | page      |
      | inventory |
      | product   |
      | checkout  |

  @Positive
  Scenario: View a product in the shopping cart
    When I have added a product from the Inventory page to the cart
    And I click on the shopping cart icon
    Then I should see the product I added on the Cart page
    And the cart badge should display the number of products added

  @Positive
  Scenario: View multiple products in the shopping cart
    When I have added all visible products from the Inventory page to the cart
    And I click on the shopping cart icon
    Then I should see the list of products I added on the Cart page
    And the cart badge should display the total number of products added

  @Negative
  Scenario: No products in the shopping cart
    When I click on the shopping cart icon without adding any products from the Inventory page to the cart
    Then I should not see any products listed on the Cart page
    And the cart badge should not display any product count

  @Positive
  Scenario: View a product in the shopping cart from Product page
    When I have added a product from the Product page to the cart
    And I click on the shopping cart icon
    Then I should see the product I added on the Cart page
    And the cart badge should display the number of products added

  @Positive
  Scenario: Remove a product from the cart
    When I am on the Cart page with a list of products
    And I click the remove button on a product
    Then I should see the product removed from the list
    And the cart badge should update to reflect the current number of products

  @Positive
  Scenario: Remove all products from the cart
    When I am on the Cart page with a list of products
    And I click the remove button on each product in the list
    Then I should see all products removed from the list
    And the cart badge should not display any product count

  @Positive
  Scenario: Navigate to a product page from the Cart page
    When I am on the Cart page with a list of products
    And I click on a product name
    Then I should be directed to the corresponding product page

  @Positive
  Scenario: Navigate to the Inventory page from the Cart page
    When I am on the Cart page with a list of products
    And I click the Continue Shopping button
    Then I should be directed to the Inventory page

  @Positive
  Scenario: Navigate to the Checkout page from the Cart page
    When I am on the Cart page with a list of products
    And I click the Checkout button
    Then I should be directed to the Checkout page

