@Login
Feature: Login functionality on SauceDemo

  @Positive
  Scenario Outline: User login with valid credentials
    Given I am on the SauceDemo login page
    When I enter a valid username "<username>" and a valid password "<password>"
    And I click on the login button
    Then I should be redirected to the inventory page

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |
      | error_user              | secret_sauce |
      | visual_user             | secret_sauce |

  @Negative
  Scenario Outline: User login with valid credentials
    Given I am on the SauceDemo login page
    When I enter a invalid username "<username>" and a valid password "<password>"
    And I click on the login button
    Then I should see an "<error>" message

    Examples:
      | username        | password     | error                                                                     |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
      | invalid         | invalid      | Epic sadface: Username and password do not match any user in this service |

  @Negative
  Scenario Outline: User login with empty username or password
    Given I am on the SauceDemo login page
    When I click the login button with empty "<data>"
    Then I should see an "<error>" message

    Examples:
      | data           | error                              |
      | empty username | Epic sadface: Username is required |
      | empty password | Epic sadface: Password is required |

  @Positive
  Scenario: User can close login error message
    Given I am on the SauceDemo login page
    When I fail to login and see error message
    Then I could close that error message

