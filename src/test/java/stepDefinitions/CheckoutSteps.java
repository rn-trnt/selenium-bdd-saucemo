package stepDefinitions;

import hook.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.*;

public class CheckoutSteps {
  WebDriver driver = Hooks.getDriver();
  LoginPage loginPage;
  InventoryPage inventoryPage;
  ProductPage productPage;
  CartPage cartPage;
  CheckOutPage checkOutPage;

  int index = 0;
  String actualProductName;
  String expectedProductName;

  @Given("I am logged into SauceDemo with standard user")
  public void i_am_logged_into_sauce_demo_with_standard_user() throws InterruptedException {
    loginPage = new LoginPage(driver);
    loginPage.enterUsername("standard_user");
    loginPage.enterPassword("secret_sauce");
    loginPage.clickLoginButton();

    inventoryPage = new InventoryPage(driver);
    productPage = new ProductPage(driver);
    cartPage = new CartPage(driver);
    checkOutPage = new CheckOutPage(driver);

    Thread.sleep(1000);
  }

  @When("I add a product to the checkout")
  public void i_add_a_product_to_the_checkout() throws InterruptedException {
    inventoryPage.getAddToCartButton(index).click();
    inventoryPage.clickCartIcon();
    cartPage.clickCheckOutButton();

    Thread.sleep(1000);
  }

  @When("I complete the checkout process with valid customer information {string}, {string}, {string}")
  public void i_complete_the_checkout_process_with_valid_customer_information(String firstName, String lastName, String zipCode) throws InterruptedException {
    checkOutPage.enterFirstName(firstName);
    checkOutPage.enterLastName(lastName);
    checkOutPage.enterZipCode(zipCode);
    checkOutPage.clickContinueButton();

    Thread.sleep(1000);
  }

  @Then("I should see my product in the checkout overview")
  public void i_should_see_my_product_in_the_checkout_overview() throws InterruptedException {
    Hooks.setTemporarilyImplicitWait(1);
    if (checkOutPage.isCheckOutSummaryContainerDisplayed()) {
      Assert.assertFalse(checkOutPage.getCartItems().isEmpty(), "No products listed in checkout overview ...");
    } else {
      Assert.fail("Checkout overview page didn't load properly ...");
    }
    Hooks.restoreImplicitWait();

    Thread.sleep(1000);
  }

  @Then("I finish the checkout process")
  public void i_finish_the_checkout_process() throws InterruptedException {
    checkOutPage.clickFinishButton();

    Thread.sleep(1000);
  }

  @When("I add multiple products to the checkout")
  public void i_add_multiple_products_to_the_checkout() throws InterruptedException {
    var products = inventoryPage.getAddToCartButtons();
    for (WebElement product : products) {
      product.click();
    }
    inventoryPage.clickCartIcon();
    cartPage.clickCheckOutButton();

    Thread.sleep(100);
  }

  @Then("I should see all my products in the checkout overview")
  public void i_should_see_all_my_products_in_the_checkout_overview() throws InterruptedException {
    if (checkOutPage.isCheckOutSummaryContainerDisplayed()) {
      Assert.assertEquals(checkOutPage.getCartItems().size(), Integer.valueOf(checkOutPage.getCartIconBadgeValue()), "Listed products in checkout overview didn't match ...");
    } else {
      Assert.fail("Checkout overview page didn't load properly ...");
    }

    Thread.sleep(1000);
  }

  @When("I leave {string} blank")
  public void i_leave_blank(String information) throws InterruptedException {
    switch (information.toLowerCase()) {
      case "first name":
        checkOutPage.enterLastName("Last Name");
        checkOutPage.enterZipCode("Zip Code");
        break;
      case "last name":
        checkOutPage.enterFirstName("First Name");
        checkOutPage.enterZipCode("Zip Code");
        break;
      case "postal code":
        checkOutPage.enterFirstName("First Name");
        checkOutPage.enterLastName("Last Name");
        break;
      default:
        Assert.fail("Unknown " + information + " ...");
    }

    Thread.sleep(1000);
  }

  @When("I attempt to continue")
  public void i_attempt_to_continue() throws InterruptedException {
    checkOutPage.clickContinueButton();

    Thread.sleep(1000);
  }

  @Then("I should see an error message saying {string}")
  public void i_should_see_an_error_message_saying(String error) throws InterruptedException {
    Assert.assertEquals(checkOutPage.getErrorMessage(), error, "Error message didn't match ...");

    Thread.sleep(1000);
  }

  @When("I attempt to continue with missing customer information")
  public void i_attempt_to_continue_with_missing_customer_information() throws InterruptedException {
    checkOutPage.clickContinueButton();

    Thread.sleep(1000);
  }

  @Then("I should be able to close the error message")
  public void i_should_be_able_to_close_the_error_message() throws InterruptedException {
    checkOutPage.clickErrorButton();

    Hooks.setTemporarilyImplicitWait(1);
    Assert.assertFalse(checkOutPage.isErrorMessageDisplayed(), "Error Message still displayed ...");
    Hooks.restoreImplicitWait();

    Thread.sleep(1000);
  }

  @When("I am on the Checkout Overview page with a list of products")
  public void i_am_on_the_checkout_overview_page_with_a_list_of_products() throws InterruptedException {
    var products = inventoryPage.getAddToCartButtons();
    for (WebElement product : products) {
      product.click();
    }
    inventoryPage.clickCartIcon();
    cartPage.clickCheckOutButton();

    checkOutPage.enterFirstName("First Name");
    checkOutPage.enterLastName("Last Name");
    checkOutPage.enterZipCode("Zip Code");
    checkOutPage.clickContinueButton();

    Thread.sleep(1000);
  }

  @When("I click the product name")
  public void i_click_the_product_name() throws InterruptedException {
    expectedProductName = checkOutPage.getProductNames().get(index).getText();
    checkOutPage.getProductNames().get(index).click();

    Thread.sleep(1000);
  }

  @Then("I should be directed to the relevant product page.")
  public void i_should_be_directed_to_the_relevant_product_page() throws InterruptedException {
    actualProductName = productPage.getProductName().getText();
    Assert.assertEquals(actualProductName, expectedProductName, "Different product name ...");

    Thread.sleep(1000);
  }


  @When("I click the Cancel button")
  public void i_click_the_cancel_button() throws InterruptedException {
    checkOutPage.clickCancelButton();

    Thread.sleep(1000);
  }

  @Then("I should be redirected to the Inventory page")
  public void i_should_be_redirected_to_the_inventory_page() throws InterruptedException {
    Assert.assertTrue(inventoryPage.isInventoryPageContainerDisplayed(), "Inventory Page not loaded properly ...");

    Thread.sleep(1000);
  }

  @When("I complete the checkout process")
  public void i_complete_the_checkout_process() throws InterruptedException {
    var products = inventoryPage.getAddToCartButtons();
    for (WebElement product : products) {
      product.click();
    }
    inventoryPage.clickCartIcon();
    cartPage.clickCheckOutButton();

    checkOutPage.enterFirstName("First Name");
    checkOutPage.enterLastName("Last Name");
    checkOutPage.enterZipCode("Zip Code");
    checkOutPage.clickContinueButton();
    checkOutPage.clickFinishButton();

    Thread.sleep(1000);
  }

  @When("I click the Back Home button")
  public void i_click_the_back_home_button() throws InterruptedException {
    Hooks.setTemporarilyImplicitWait(1);
    if(checkOutPage.isCheckOutCompleteContainerDisplayed()){
      checkOutPage.clickBackHomeButton();
    } else {
      Assert.fail("Checkout failed ...");
    }
    Hooks.restoreImplicitWait();

    Thread.sleep(1000);
  }

}
