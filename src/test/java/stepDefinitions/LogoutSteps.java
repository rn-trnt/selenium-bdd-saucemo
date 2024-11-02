package stepDefinitions;

import hook.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.*;

public class LogoutSteps {
  WebDriver driver = Hooks.getDriver();
  LoginPage loginPage;
  InventoryPage inventoryPage;
  ProductPage productPage;
  CartPage cartPage;
  CheckOutPage checkOutPage;

  int index = 0;

  @Given("I am logged into SauceDemo as a standard user")
  public void i_am_logged_into_sauce_demo_as_a_standard_user() throws InterruptedException {
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

  @When("I am navigating to {string} page")
  public void i_am_navigating_to_page(String page) throws InterruptedException {
    switch (page.toLowerCase()){
      case "inventory":
        Assert.assertTrue(inventoryPage.isInventoryPageContainerDisplayed(), "Inventory Page not loaded properly ...");
        break;
      case "product":
        inventoryPage.getProductName(index).click();
        Assert.assertTrue(productPage.isProductPageContainerDisplayed(), "Product Page not loaded properly ...");
        break;
      case "cart":
        inventoryPage.clickCartIcon();
        Assert.assertTrue(cartPage.isCartPageContainerDisplayed(), "Cart Page not loaded properly ...");
        break;
      case "checkout":
        inventoryPage.clickCartIcon();
        cartPage.clickCheckOutButton();
        Assert.assertTrue(checkOutPage.isCheckOutPageContainerDisplayed(), "Checkout Page not loaded properly ...");
        break;
      default:
        Assert.fail("SauceDemo not loaded properly ...");
    }

    Thread.sleep(1000);
  }

  @When("I click on the side menu button from {string} page")
  public void i_click_on_the_side_menu_button_from_page(String page) throws InterruptedException {
   switch (page.toLowerCase()){
     case "inventory" :
       inventoryPage.clickSideBarMenuIcon();
       break;
     case "product" :
         productPage.clickSideBarMenuIcon();
         break;
     case "cart" :
       cartPage.clickSideBarMenuIcon();
       break;
     case "checkout":
       checkOutPage.clickSideBarMenuIcon();
       break;
     default:
       Assert.fail("Sidebar Menu not detected ...");
   }

   Thread.sleep(1000);
  }

  @When("I select the logout option from {string} page")
  public void i_select_the_logout_option_from_page(String page) throws InterruptedException {
    switch (page.toLowerCase()){
      case "inventory" :
        inventoryPage.clickLogoutButton();
        break;
      case "product" :
        productPage.clickLogoutButton();
        break;
      case "cart" :
        cartPage.clickLogoutButton();
        break;
      case "checkout":
        checkOutPage.clickLogoutButton();
        break;
      default:
        Assert.fail("Logout Button not detected ...");
    }

    Thread.sleep(1000);
  }

  @Then("I should be redirected to the login page")
  public void i_should_be_redirected_to_the_login_page() throws InterruptedException {
    Assert.assertTrue(loginPage.isLoginPageContainerDisplayed(), "Login Page not loaded properly ...");

    Thread.sleep(1000);
  }
}
