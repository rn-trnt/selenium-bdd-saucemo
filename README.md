# SauceDemo Automation Testing Project

This project automates the testing of key functionalities on [SauceDemo](https://www.saucedemo.com/) using **Selenium with Java**, **Cucumber**, and **TestNG** with a **BDD** approach. The automation tests cover essential features such as **login, inventory, cart, checkout, and logout**.

Please note: there may be areas for improvement in the logic or test structure used in this project.

## Project Overview

The purpose of this project is to implement a reliable automated testing framework that validates critical functionalities on the SauceDemo website, leveraging the BDD (Behavior Driven Development) approach. Tests are implemented to verify both positive and negative scenarios.

## Technologies Used

- **Selenium WebDriver**: Browser automation
- **Java**: Main programming language
- **Cucumber**: BDD framework for Gherkin syntax
- **TestNG**: Test execution and reporting
- **Extent Reports**: For generating HTML test reports

## Test Scope

### 1. Login Functionality
Tests focus on verifying both valid and invalid login scenarios.

  - Login with valid credentials for multiple users.
  - Closing login error messages after failed login attempts.
  - Invalid login with incorrect credentials.
  - Login attempt with missing username or password.

### 2. Inventory Functionality
Tests for actions available on the inventory page, such as adding/removing products and sorting.

  - Adding and removing single or multiple products from the cart.
  - Sorting products by price or name.
  - Opening individual product pages via image or name.
  - Toggling the side menu display.

### 3. Cart Functionality
Tests cover actions related to viewing and managing the shopping cart.

  - Viewing single or multiple products in the cart.
  - Removing products from the cart.
  - Navigating to the checkout page.
  - Viewing an empty cart when no products are added.

### 4. Checkout Functionality
Tests for completing the checkout process, including form validation.

  - Successful checkout with valid customer information.
  - Viewing products in the checkout overview.
  - Navigating between the checkout overview and product pages.
  - Attempting to proceed without filling required customer information fields.

### 5. Logout Functionality
Tests for successfully logging out from various pages within the application.

  - Logging out from inventory, product, cart, and checkout pages.

## Project Setup

1. **Clone the Repository**:
```bash
git clone https://github.com/yourusername/saucedemo-automation.git
cd saucedemo-automation
```

2. **Install Dependencies: Ensure that you have Maven installed, then run:** 
```bash
mvn clean install
```

## Execution    

1. Run All Tests: You can run all the tests using Maven with the following command:
```bash
mvn test
```

2. Run Tests via TestNG XML located in the `runners` folder.

## Reports

HTML test results are generated using Extent Reports and can be found in the `reports` folder after test execution. Reports include details such as scenario names, pass/fail status, timestamps, and tags as categories for better filtering.


























