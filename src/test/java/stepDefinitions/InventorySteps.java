package stepDefinitions;

import hook.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.InventoryPage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;

public class InventorySteps {
  WebDriver driver = Hooks.getDriver();
  LoginPage loginPage;
  InventoryPage inventoryPage;
  ProductPage productPage;

  int index = 0;
  String productNameInventory;
  String productNameProduct;

  // Click single Add To Cart button
  public void clickATC(int index) {
    Hooks.setTemporarilyImplicitWait(1);
    try {
      inventoryPage.clickAddToCartButton(index);
    } catch (Exception e) {
      Assert.fail("ERROR: Can't find product ...");
    }
    Hooks.restoreImplicitWait();
  }

  // Click every visible Add To Cart button
  public void clickATC() {
    Hooks.setTemporarilyImplicitWait(1);
    try {
      inventoryPage.clickAddToCartButton();
    } catch (Exception e) {
      Assert.fail("ERROR: Can't find product ...");
    }
    Hooks.restoreImplicitWait();
  }

  @Given("I am logged in as a standard user")
  public void i_am_logged_in_as_a_standard_user() throws InterruptedException {
    loginPage = new LoginPage(driver);
    loginPage.enterUsername("standard_user");
    loginPage.enterPassword("secret_sauce");
    loginPage.clickLoginButton();

    inventoryPage = new InventoryPage(driver);
    Thread.sleep(1000);
  }

  @When("I add a single product on Inventory page to the cart")
  public void i_add_a_single_product_on_inventory_page_to_the_cart() throws InterruptedException {
    clickATC(index);
    Thread.sleep(1000);
  }

  @When("I add a all visible products on Inventory page to the cart")
  public void i_add_a_all_visible_products_on_inventory_page_to_the_cart() throws InterruptedException {
    clickATC();
    Thread.sleep(1000);
  }

  @Then("the product should be added to the cart")
  public void the_product_should_be_added_to_the_cart() throws InterruptedException {
    Assert.assertTrue(inventoryPage.isCartIconBadgeDisplayed(), "Failed to add product to the cart ...");
    Thread.sleep(1000);
  }

  @Then("the cart badge should be activated and display the number of products added")
  public void the_cart_badge_should_be_activated_and_display_the_number_of_products_added() throws InterruptedException {
    int selectedProduct = inventoryPage.getRemoveFromCartButtons().size();
    Assert.assertEquals(inventoryPage.getCartIconBadgeValue(), Integer.toString(selectedProduct), "Shopping cart badge didn't match ...");
    Thread.sleep(1000);
  }

  @When("I remove a single product on Inventory page from the cart")
  public void i_remove_a_single_product_on_inventory_page_from_the_cart() throws InterruptedException {
    inventoryPage.clickRemoveFromCartButton(index);
    Thread.sleep(1000);
  }

  @When("I remove all visible products on Inventory page from the cart")
  public void i_remove_all_visible_products_on_inventory_page_from_the_cart() throws InterruptedException {
    inventoryPage.clickRemoveFromCartButton();
    Thread.sleep(1000);
  }

  @Then("the product should have add to cart button")
  public void the_product_should_have_add_to_cart_button() throws InterruptedException {
    Assert.assertEquals(inventoryPage.getAddToCartButton(index).getText(), "Add to cart", "Product didn't have Add to cart button ...");
    Thread.sleep(1000);
  }

  @Then("the products should have add to cart button")
  public void the_products_should_have_add_to_cart_button() throws InterruptedException {
    var items = inventoryPage.getAddToCartButtons();
    for (WebElement item : items) {
      Assert.assertEquals(item.getText(), "Add to cart", "Product didn't have Add to cart button ...");
    }

    Thread.sleep(1000);
  }

  @Then("the cart badge should be updated and display the current number of products")
  public void the_cart_badge_should_be_updated_and_display_the_current_number_of_products() throws InterruptedException {
    Hooks.setTemporarilyImplicitWait(1);
    int selectedProduct = inventoryPage.getRemoveFromCartButtons().size();

    if (selectedProduct > 0) {
      Assert.assertEquals(inventoryPage.getCartIconBadgeValue(), Integer.toString(selectedProduct), "Cart icon badge didn't match ...");
    } else {
      Assert.assertFalse(inventoryPage.isCartIconBadgeDisplayed(), "Cart icon badge still displayed ...");
    }
    Thread.sleep(1000);
  }

  @When("I sort the products by {string}")
  public void i_sort_the_products_by(String sortOption) throws InterruptedException {
    inventoryPage.sortProduct(sortOption);

    Thread.sleep(1000);
  }

  @Then("the products should be displayed in the {string} order")
  public void the_products_should_be_displayed_in_the_order(String sortOrder) throws InterruptedException {
    switch (sortOrder.toLowerCase()) {
      case "alphabetical":
        Assert.assertTrue(inventoryPage.isSortedAlphabetically(), "Products are not sorted alphabetically!");
        break;
      case "reverse alphabetical":
        Assert.assertTrue(inventoryPage.isSortedReverseAlphabetically(), "Products are not sorted in reverse alphabetical order!");
        break;
      case "ascending":
        Assert.assertTrue(inventoryPage.isSortedAscending(), "Products are not sorted by price (low to high)!");
        break;
      case "descending":
        Assert.assertTrue(inventoryPage.isSortedDescending(), "Products are not sorted by price (high to low)!");
        break;
      default:
        Assert.fail("Unknown sort order: " + sortOrder + " ...");
    }

    Thread.sleep(1000);
  }

  @Then("the sort dropdown text should show {string}")
  public void the_sort_dropdown_text_should_show(String sortOption) throws InterruptedException {
    Assert.assertEquals(inventoryPage.getSortOption(), sortOption, "Unknown sort order: " + sortOption + " ...");

    Thread.sleep(1000);
  }

  @When("I click the product from product {string}")
  public void i_click_the_product_from_product(String type) throws InterruptedException {
    productNameInventory = inventoryPage.getProductName(index).getText();

    Hooks.setTemporarilyImplicitWait(1);
    try {
      switch (type.toLowerCase()) {
        case "image":
          inventoryPage.getProductImage(index).click();
          break;
        case "name":
          inventoryPage.getProductName(index).click();
          break;
      }
    } catch (Exception e) {
      Assert.fail("Can't access product from product " + type + " ...");
    }
    Hooks.restoreImplicitWait();

    productPage = new ProductPage(driver);

    Thread.sleep(1000);
  }

  @Then("I should be redirected to the corresponding product page")
  public void i_should_be_redirected_to_the_corresponding_product_page() throws InterruptedException {
    productNameProduct = productPage.getProductName().getText();
    Assert.assertEquals(productNameProduct, productNameInventory, "Product didn't match ...");

    Thread.sleep(1000);
  }

  @When("I click the side menu icon")
  public void i_click_the_side_menu_icon() throws InterruptedException {
    inventoryPage.clickSideBarMenuIcon();

    Thread.sleep(1000);
  }

  @Then("the side menu should be displayed on the Inventory page")
  public void the_side_menu_should_be_displayed_on_the_inventory_page() throws InterruptedException {
    Assert.assertTrue(inventoryPage.isSideBarMenuDisplayed(), "Side menu not displayed ...");

    Thread.sleep(1000);
  }

  @Then("the side menu should disappear after I click the close icon")
  public void the_side_menu_should_disappear_after_i_click_the_close_icon() throws InterruptedException {
    inventoryPage.closeSideBarMenu();
    Thread.sleep(1000);
    Assert.assertFalse(inventoryPage.isSideBarMenuDisplayed(), "Side menu still displayed ...");
  }
}
