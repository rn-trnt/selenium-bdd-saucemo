package stepDefinitions;

import hook.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.*;

public class CartSteps {

  WebDriver driver = Hooks.getDriver();
  LoginPage loginPage;
  InventoryPage inventoryPage;
  ProductPage productPage;
  CartPage cartPage;
  CheckOutPage checkOutPage;

  int index = 0;
  int inventoryPageProductList;
  int cartPageProductList;

  String expectedProductName;
  String actualProductName;

  @Given("I am logged in with standard user")
  public void i_am_logged_in_with_standard_user() throws InterruptedException {
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

  @When("I click on the shopping cart icon from {string} page")
  public void i_click_on_the_shopping_cart_icon_from_page(String page) throws InterruptedException {
    switch (page.toLowerCase()) {
      case "inventory":
        inventoryPage.clickCartIcon();
        break;
      case "product":
        inventoryPage.getProductName(index).click();
        Thread.sleep(500);
        productPage.clickCartIcon();
        break;
      case "checkout":
        inventoryPage.clickCartIcon();
        Thread.sleep(500);
        cartPage.clickCheckOutButton();
        Thread.sleep(500);
        checkOutPage.clickCartIcon();
        break;
      default:
        Assert.fail("SauceDemo not loaded properly ...");
    }

    Thread.sleep(1000);
  }

  @Then("I should be redirected to the shopping cart page")
  public void i_should_be_redirected_to_the_shopping_cart_page() throws InterruptedException {
    Assert.assertTrue(cartPage.isCartPageContainerDisplayed(), "Cart page not loaded properly ...");

    Thread.sleep(1000);
  }

  @When("I have added a product from the Inventory page to the cart")
  public void i_have_added_a_product_from_the_inventory_page_to_the_cart() throws InterruptedException {
    inventoryPage.getAddToCartButton(index).click();
    expectedProductName = inventoryPage.getProductName(index).getText();

    Thread.sleep(1000);
  }

  @When("I click on the shopping cart icon")
  public void i_click_on_the_shopping_cart_icon() throws InterruptedException {
    inventoryPage.clickCartIcon();

    Thread.sleep(1000);
  }

  @Then("I should see the product I added on the Cart page")
  public void i_should_see_the_product_i_added_on_the_cart_page() throws InterruptedException {
    try {
      if (cartPage.isListProductsDisplayed()) {
        actualProductName = cartPage.getListProductName(index).getText();
        Assert.assertEquals(actualProductName, expectedProductName, "Product didn't match ...");
      }
    } catch (Exception e) {
      Assert.fail("No product displayed ...");
    }

    Thread.sleep(1000);
  }

  @Then("the cart badge should display the number of products added")
  public void the_cart_badge_should_display_the_number_of_products_added() throws InterruptedException {
    Assert.assertEquals(Integer.valueOf(cartPage.getCartIconBadgeValue()), cartPage.getListProducts().size(), "Number of products didn't match ...");
    Thread.sleep(1000);
  }

  @When("I have added all visible products from the Inventory page to the cart")
  public void i_have_added_all_visible_products_from_the_inventory_page_to_the_cart() throws InterruptedException {
    var products = inventoryPage.getAddToCartButtons();
    for (WebElement product : products) {
      product.click();
    }

    inventoryPageProductList = inventoryPage.getProductNames().size();

    Thread.sleep(1000);
  }

  @Then("I should see the list of products I added on the Cart page")
  public void i_should_see_the_list_of_products_i_added_on_the_cart_page() throws InterruptedException {
    try {
      if (cartPage.isListProductsDisplayed()) {
        cartPageProductList = cartPage.getListProducts().size();
        Assert.assertEquals(cartPageProductList, inventoryPageProductList, "Product didn't match ...");
      }
    } catch (Exception e) {
      Assert.fail("Product list didn't match ...");
    }

    Thread.sleep(1000);
  }

  @Then("the cart badge should display the total number of products added")
  public void the_cart_badge_should_display_the_total_number_of_products_added() throws InterruptedException {
    Assert.assertEquals(Integer.valueOf(cartPage.getCartIconBadgeValue()), cartPageProductList, "Number of products in the cart didn't match ...");
    Thread.sleep(1000);
  }

  @When("I click on the shopping cart icon without adding any products from the Inventory page to the cart")
  public void i_click_on_the_shopping_cart_icon_without_adding_any_products_from_the_inventory_page_to_the_cart() throws InterruptedException {
    inventoryPage.clickCartIcon();
    Thread.sleep(1000);
  }

  @Then("I should not see any products listed on the Cart page")
  public void i_should_not_see_any_products_listed_on_the_cart_page() throws InterruptedException {
    Hooks.setTemporarilyImplicitWait(1);
    Assert.assertFalse(cartPage.isListProductsDisplayed(), "Product didn't match ...");
    Hooks.restoreImplicitWait();

    Thread.sleep(1000);
  }

  @Then("the cart badge should not display any product count")
  public void the_cart_badge_should_not_display_any_product_count() throws InterruptedException {
    Hooks.setTemporarilyImplicitWait(1);
    Assert.assertFalse(cartPage.isCartIconBadgeDisplayed());
    Hooks.restoreImplicitWait();

    Thread.sleep(1000);
  }

  @When("I have added a product from the Product page to the cart")
  public void i_have_added_a_product_from_the_product_page_to_the_cart() throws InterruptedException {
    inventoryPage.getProductImage(index).click();
    expectedProductName = productPage.getProductName().getText();
    productPage.clickAddToCartButton();

    Thread.sleep(1000);
  }

  @When("I am on the Cart page with a list of products")
  public void i_am_on_the_cart_page_with_a_list_of_products() throws InterruptedException {
    var products = inventoryPage.getAddToCartButtons();
    for(WebElement product : products){
      product.click();
    }

    inventoryPage.clickCartIcon();

    Thread.sleep(1000);
  }

  @When("I click the remove button on a product")
  public void i_click_the_remove_button_on_a_product() throws InterruptedException {
    cartPageProductList = cartPage.getListProducts().size();

    cartPage.getListProductRemoveButton(index).click();
    cartPage.getListProductRemoveButton(index).click();

    Thread.sleep(1000);
  }

  @Then("I should see the product removed from the list")
  public void i_should_see_the_product_removed_from_the_list() throws InterruptedException {
    Assert.assertNotEquals(cartPageProductList, cartPage.getListProducts().size(), "Number of products should be different ...");

    Thread.sleep(1000);
  }

  @Then("the cart badge should update to reflect the current number of products")
  public void the_cart_badge_should_update_to_reflect_the_current_number_of_products() throws InterruptedException {
    Assert.assertEquals(Integer.valueOf(cartPage.getCartIconBadgeValue()), cartPage.getListProducts().size(), "Number of products in the cart didn't match ...");

    Thread.sleep(1000);
  }

  @When("I click the remove button on each product in the list")
  public void i_click_the_remove_button_on_each_product_in_the_list() throws InterruptedException {
    var products = cartPage.getListProductRemoveButtons();
    for(WebElement product : products){
      product.click();
    }

    Thread.sleep(1000);
  }

  @Then("I should see all products removed from the list")
  public void i_should_see_all_products_removed_from_the_list() throws InterruptedException {
    Hooks.setTemporarilyImplicitWait(1);
    Assert.assertFalse(cartPage.isListProductsDisplayed(), "List products should be empty ...");
    Hooks.restoreImplicitWait();

    Thread.sleep(1000);
  }

  @When("I click on a product name")
  public void i_click_on_a_product_name() throws InterruptedException {
    expectedProductName = cartPage.getListProductName(index).getText();
    cartPage.getListProductName(index).click();

    Thread.sleep(1000);
  }

  @Then("I should be directed to the corresponding product page")
  public void i_should_be_directed_to_the_corresponding_product_page() throws InterruptedException {
    actualProductName = productPage.getProductName().getText();
    Assert.assertEquals(actualProductName, expectedProductName, "Different product name ...");

    Thread.sleep(1000);
  }

  @When("I click the Continue Shopping button")
  public void i_click_the_continue_shopping_button() throws InterruptedException {
    cartPage.clickContinueShoppingButton();

    Thread.sleep(1000);
  }

  @Then("I should be directed to the Inventory page")
  public void i_should_be_directed_to_the_inventory_page() throws InterruptedException {
    Assert.assertTrue(inventoryPage.isInventoryPageContainerDisplayed(), "Inventory page not loaded properly ...");

    Thread.sleep(1000);
  }

  @When("I click the Checkout button")
  public void i_click_the_checkout_button() throws InterruptedException {
    cartPage.clickCheckOutButton();

    Thread.sleep(1000);
  }

  @Then("I should be directed to the Checkout page")
  public void i_should_be_directed_to_the_checkout_page() throws InterruptedException {
    Assert.assertTrue(checkOutPage.isCheckOutPageContainerDisplayed(), "Checkout page not loaded properly ...");

    Thread.sleep(1000);
  }
}
