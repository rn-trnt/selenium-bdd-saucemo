@Logout
Feature: Logout functionality on SauceDemo

  Background:
    Given I am logged into SauceDemo as a standard user

  @Positive
  Scenario Outline: Successful logout
    When I am navigating to "<page>" page
    And I click on the side menu button from "<page>" page
    And I select the logout option from "<page>" page
    Then I should be redirected to the login page

    Examples:
      | page      |
      | inventory |
      | product   |
      | cart      |
      | checkout  |
