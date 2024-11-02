package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the Inventory page of the application, containing methods to interact with product listings and other elements on the page.
 */
public class InventoryPage {

  WebDriver driver;

  /**
   * Initializes the InventoryPage with the specified WebDriver.
   *
   * @param driver the WebDriver to interact with the page.
   */
  public InventoryPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  // Locators for elements on the Checkout page
  @FindBy(xpath = "//div[@id='inventory_container']//div[@id='inventory_container']")
  WebElement inventoryPageContainer;

  @FindBy(className = "title")
  WebElement title;

  @FindBy(xpath = "//div[@class = 'pricebar']//*[contains(text(), 'Add to cart')]")
  List<WebElement> addToCartButtons;

  @FindBy(xpath = "//div[@class = 'pricebar']//*[contains(text(), 'Remove')]")
  List<WebElement> removeFromCartButtons;

  @FindBy(xpath = "//div[@class='inventory_item']")
  List<WebElement> productCards;

  @FindBy(xpath = "//div[@class='inventory_item_img']")
  List<WebElement> productImages;

  @FindBy(xpath = "//div[@class='inventory_item_name ']")
  List<WebElement> productNames;

  @FindBy(xpath = "//div[@class = 'pricebar']//div[@class='inventory_item_price']")
  List<WebElement> productPrices;

  @FindBy(xpath = "//select[@class='product_sort_container']")
  WebElement sortDropdown;

  @FindBy(xpath = "//div[@id='shopping_cart_container']")
  WebElement cartIcon;

  @FindBy(xpath = "//a[@class='shopping_cart_link']//span")
  WebElement cartIconBadge;

  @FindBy(xpath = "//div[@class='bm-burger-button']")
  WebElement sideBarMenuIcon;

  @FindBy(xpath = "//button[@id='react-burger-cross-btn']")
  WebElement sideBarCloseIcon;

  @FindBy(xpath = "//div[@class='bm-menu'] ")
  WebElement sideBarMenu;

  @FindBy(xpath = "//a[@id='logout_sidebar_link']")
  WebElement logoutButton;

  // Methods for interacting with elements on the Inventory page

  /**
   * Checks if the Inventory page container is displayed.
   *
   * @return true if the inventory page container is visible; false otherwise.
   */
  public boolean isInventoryPageContainerDisplayed() {
    return inventoryPageContainer.isDisplayed();
  }

  /**
   * Checks if the page title (logo) is displayed.
   *
   * @return true if the title element is visible; false otherwise.
   */
  public boolean isLogoDisplayed() {
    return title.isDisplayed();
  }

  /**
   * Retrieves the "Add to cart" button for a specific product.
   *
   * @param index the index of the product.
   * @return the WebElement for the "Add to cart" button.
   */
  public WebElement getAddToCartButton(int index) {
    return addToCartButtons.get(index);
  }

  /**
   * Retrieves all "Add to cart" buttons on the page.
   *
   * @return a list of WebElements representing "Add to cart" buttons.
   */
  public List<WebElement> getAddToCartButtons() {
    return addToCartButtons;
  }

  /**
   * Clicks the "Add to cart" button for a specific product.
   *
   * @param index the index of the product to add to the cart.
   */
  public void clickAddToCartButton(int index) {
    if (areProductPresent()) {
      getAddToCartButton(index).click();
    } else {
      throw new NoSuchElementException("ERROR: No products are present to add to cart ...");
    }
  }

  /**
   * Clicks all "Add to cart" buttons on the page.
   */
  public void clickAddToCartButton() {
    if (areProductPresent()) {
      for (WebElement cart : addToCartButtons) {
        cart.click();
      }
    } else {
      throw new NoSuchElementException("ERROR: No products are present to add to cart ...");
    }
  }

  /**
   * Retrieves the "Remove" button for a specific product.
   *
   * @param index the index of the product.
   * @return the WebElement for the "Remove" button.
   */
  public WebElement getRemoveFromCartButton(int index) {
    return removeFromCartButtons.get(index);
  }

  /**
   * Retrieves all "Remove" buttons on the page.
   *
   * @return a list of WebElements representing "Remove" buttons.
   */
  public List<WebElement> getRemoveFromCartButtons() {
    return removeFromCartButtons;
  }

  /**
   * Clicks the "Remove" button for a specific product.
   *
   * @param index the index of the product to remove from the cart.
   */
  public void clickRemoveFromCartButton(int index) {
    getRemoveFromCartButton(index).click();
  }

  /**
   * Clicks all "Remove" buttons on the page.
   */
  public void clickRemoveFromCartButton() {
    for (WebElement remove : removeFromCartButtons) {
      remove.click();
    }
  }

  /**
   * Checks if any products are displayed on the Inventory page.
   *
   * @return true if products are present; false otherwise.
   */
  public boolean areProductPresent() {
    return !productCards.isEmpty();
  }

  /**
   * Retrieves the name element of a specific product.
   *
   * @param index the index of the product.
   * @return the WebElement representing the product name.
   */
  public WebElement getProductName(int index) {
    return productNames.get(index);
  }

  /**
   * Retrieves all product names displayed on the page.
   *
   * @return a list of WebElements representing product names.
   */
  public List<WebElement> getProductNames() {
    return productNames;
  }

  /**
   * Retrieves the image element of a specific product.
   *
   * @param index the index of the product.
   * @return the WebElement representing the product image.
   */
  public WebElement getProductImage(int index) {
    return productImages.get(index);
  }

  /**
   * Retrieves all product images displayed on the page.
   *
   * @return a list of WebElements representing product images.
   */
  public List<WebElement> getProductImages() {
    return productImages;
  }

  /**
   * Retrieves the price of a specific product as a string.
   *
   * @param index the index of the product.
   * @return the price of the product as a string, without the dollar sign.
   */
  public String getProductPrice(int index) {
    return productPrices.get(index).getText().replace("$", "");
  }

  /**
   * Retrieves the prices of all products as a list of strings.
   *
   * @return a list of product prices as strings, without dollar signs.
   */
  public List<String> getProductPrices() {
    List<String> prices = new ArrayList<>();
    for (WebElement productPrice : productPrices) {
      prices.add(productPrice.getText().replace("$", ""));
    }
    return prices;
  }

  /**
   * Sorts the products based on the selected sorting option.
   *
   * @param selectedOption the sorting option to select.
   */
  public void sortProduct(String selectedOption) {
    Select dropdown = new Select(sortDropdown);
    dropdown.selectByVisibleText(selectedOption);
  }

  /**
   * Retrieves the currently selected sorting option.
   *
   * @return the visible text of the selected option.
   */
  public String getSortOption() {
    Select dropdown = new Select(sortDropdown);
    return dropdown.getFirstSelectedOption().getText();
  }

  /**
   * Checks if products are sorted alphabetically by name.
   *
   * @return true if products are sorted alphabetically; false otherwise.
   */
  public boolean isSortedAlphabetically() {
    List<String> unsortedNames = new ArrayList<>();
    List<String> sortedNames = new ArrayList<>();

    for (WebElement product : productNames) {
      unsortedNames.add(product.getText());
      sortedNames.add(product.getText());
    }

    Collections.sort(sortedNames);
    return unsortedNames.equals(sortedNames);
  }

  /**
   * Checks if products are sorted in reverse alphabetical order by name.
   *
   * @return true if products are sorted in reverse order; false otherwise.
   */
  public boolean isSortedReverseAlphabetically() {
    List<String> unsortedNames = new ArrayList<>();
    List<String> sortedNames = new ArrayList<>();

    for (WebElement product : productNames) {
      unsortedNames.add(product.getText());
      sortedNames.add(product.getText());
    }

    sortedNames.sort(Collections.reverseOrder());
    return unsortedNames.equals(sortedNames);
  }

  /**
   * Checks if products are sorted in ascending order by price.
   *
   * @return true if products are sorted in ascending order; false otherwise.
   */
  public boolean isSortedAscending() {
    List<Double> unsortedPrices = new ArrayList<>();
    List<Double> sortedPrices = new ArrayList<>();

    for (String price : getProductPrices()) {
      unsortedPrices.add(Double.parseDouble(price));
      sortedPrices.add(Double.parseDouble(price));
    }

    Collections.sort(sortedPrices);
    return unsortedPrices.equals(sortedPrices);
  }

  /**
   * Checks if products are sorted in descending order by price.
   *
   * @return true if products are sorted in descending order; false otherwise.
   */
  public boolean isSortedDescending() {
    List<Double> unsortedPrices = new ArrayList<>();
    List<Double> sortedPrices = new ArrayList<>();

    for (String price : getProductPrices()) {
      unsortedPrices.add(Double.parseDouble(price));
      sortedPrices.add(Double.parseDouble(price));
    }

    sortedPrices.sort(Collections.reverseOrder());
    return unsortedPrices.equals(sortedPrices);
  }

  /**
   * Clicks the cart icon to navigate to the shopping cart.
   */
  public void clickCartIcon() {
    cartIcon.click();
  }

  /**
   * Checks if the cart icon badge is displayed.
   *
   * @return true if the badge is visible; false otherwise.
   */
  public boolean isCartIconBadgeDisplayed() {
    try {
      return cartIconBadge.isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Retrieves the value of the cart icon badge.
   *
   * @return the badge value as a string, or an error message if not detected.
   */
  public String getCartIconBadgeValue() {
    if (isCartIconBadgeDisplayed()) {
      return cartIconBadge.getText();
    }
    return "ERROR: Cart badge not detected ...";
  }

  /**
   * Opens the sidebar menu by clicking its icon.
   */
  public void clickSideBarMenuIcon() {
    sideBarMenuIcon.click();
  }

  /**
   * Checks if the sidebar menu is displayed.
   *
   * @return true if the sidebar menu is visible; false otherwise.
   */
  public boolean isSideBarMenuDisplayed() {
    return sideBarMenu.isDisplayed();
  }

  /**
   * Closes the sidebar menu by clicking the close icon.
   */
  public void closeSideBarMenu() {
    sideBarCloseIcon.click();
  }

  /**
   * Logs out by clicking the logout button in the sidebar menu.
   */
  public void clickLogoutButton() {
    logoutButton.click();
  }

}