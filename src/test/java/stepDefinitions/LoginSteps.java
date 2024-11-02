package stepDefinitions;

import hook.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.InventoryPage;
import pageObjects.LoginPage;

public class LoginSteps {
  WebDriver driver = Hooks.getDriver();
  LoginPage loginPage;
  InventoryPage inventoryPage;

  @Given("I am on the SauceDemo login page")
  public void i_am_on_the_sauce_demo_login_page() throws InterruptedException {
    loginPage = new LoginPage(driver);
    Assert.assertTrue(loginPage.isLogoDisplayed(), "Login Page not loaded properly ...");

    Thread.sleep(1000);
  }

  @When("I enter a valid username {string} and a valid password {string}")
  @When("I enter a invalid username {string} and a valid password {string}")
  public void i_enter_a_valid_username_and_a_valid_password(String username, String password) throws InterruptedException {
    loginPage.enterUsername(username);
    loginPage.enterPassword(password);

    Thread.sleep(1000);
  }

  @When("I click the login button with empty {string}")
  public void emptyDataLogin(String data) throws InterruptedException {
    if (data.equals("empty username")) {
      loginPage.enterPassword("password");
      loginPage.clickLoginButton();
    } else if (data.equals("empty password")) {
      loginPage.enterUsername("username");
      loginPage.clickLoginButton();
    }

    Thread.sleep(1000);
  }

  @When("I click on the login button")
  public void i_click_on_the_login_button() throws InterruptedException {
    loginPage.clickLoginButton();

    Thread.sleep(1000);
  }

  @When("I fail to login and see error message")
  public void i_fail_to_login() throws InterruptedException {
    loginPage.clickLoginButton();

    Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed ...");

    Thread.sleep(1000);
  }

  @Then("I should be redirected to the inventory page")
  public void i_should_be_redirected_to_the_inventory_page() throws InterruptedException {
    inventoryPage = new InventoryPage(driver);
    Assert.assertTrue(inventoryPage.isLogoDisplayed(), "Inventory Page not loaded properly ...");

    Thread.sleep(1000);
  }

  @Then("I should see an {string} message")
  public void i_should_see_an_message(String error) throws InterruptedException {
    Assert.assertEquals(loginPage.getErrorMessage(), error, "Error message didn't match ...");

    Thread.sleep(1000);
  }

  @Then("I could close that error message")
  public void i_could_close_that_error_message() throws InterruptedException {
    loginPage.clickErrorButton();
    Hooks.setTemporarilyImplicitWait(1);
    Assert.assertFalse(loginPage.isErrorMessageDisplayed(), "Error Message still displayed ...");

    Thread.sleep(1000);
  }
}
