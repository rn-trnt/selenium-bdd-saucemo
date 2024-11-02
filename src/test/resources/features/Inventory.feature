@Inventory
Feature: Inventory page functionality on SauceDemo

  Background:
    Given I am logged in as a standard user

  @Positive
  Scenario: Add a single product to the cart
    When I add a single product on Inventory page to the cart
    Then the product should be added to the cart
    And the cart badge should be activated and display the number of products added

  @Positive
  Scenario: Add a all visible products to the cart
    When I add a all visible products on Inventory page to the cart
    Then the product should be added to the cart
    And the cart badge should be activated and display the number of products added

  @Positive
  Scenario: Remove a single product from the cart
    When I add a single product on Inventory page to the cart
    And I remove a single product on Inventory page from the cart
    Then the product should have add to cart button
    And the cart badge should be updated and display the current number of products

  @Positive
  Scenario: Remove a all visible product from the cart
    When I add a all visible products on Inventory page to the cart
    And I remove all visible products on Inventory page from the cart
    Then the products should have add to cart button
    And the cart badge should be updated and display the current number of products

  @Positive
  Scenario Outline: Sort products on Inventory page
    When I sort the products by "<sortOption>"
    Then the products should be displayed in the "<sortOrder>" order
    And the sort dropdown text should show "<sortOption>"

    Examples:
      | sortOption          | sortOrder            |
      | Price (low to high) | ascending            |
      | Price (high to low) | descending           |
      | Name (A to Z)       | alphabetical         |
      | Name (Z to A)       | reverse alphabetical |

  @Positive
  Scenario Outline: Open single product on Inventory Page
    When I click the product from product "<type>"
    Then I should be redirected to the corresponding product page

    Examples:
      | type  |
      | image |
      | name  |

  @Positive
  Scenario: Open and close side menu on Inventory page
    When I click the side menu icon
    Then the side menu should be displayed on the Inventory page
    And the side menu should disappear after I click the close icon



