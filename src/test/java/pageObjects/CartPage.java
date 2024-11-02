package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Represents the Cart page within the application.
 * Provides methods to interact with elements on the cart page, including viewing
 * items in the cart, removing items, and navigating to checkout or other pages.
 */
public class CartPage {

  WebDriver driver;

  /**
   * Constructor initializes elements on the Cart page.
   *
   * @param driver the WebDriver instance to interact with the browser
   */
  public CartPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  // Locators for elements on the Cart page
  @FindBy(xpath = "//div[@id='cart_contents_container']")
  WebElement cartPageContainer;

  @FindBy(xpath = "//div[@class='cart_item']")
  List<WebElement> listProducts;

  @FindBy(xpath = "//a//div[@data-test='inventory-item-name']")
  List<WebElement> listProductNames;

  @FindBy(xpath = "//div[@class='cart_item']//button")
  List<WebElement> listProductRemoveButtons;

  @FindBy(xpath = "//button[@id='checkout']")
  WebElement checkOutButton;

  @FindBy(xpath = "//button[@id='continue-shopping']")
  WebElement continueShoppingButton;

  @FindBy(xpath = "//div[@id='shopping_cart_container']")
  WebElement cartIcon;

  @FindBy(xpath = "//a[@class='shopping_cart_link']//span")
  WebElement cartIconBadge;

  @FindBy(xpath = "//div[@class='bm-burger-button']")
  WebElement sideBarMenuIcon;

  @FindBy(xpath = "//button[@id='react-burger-cross-btn']")
  WebElement sideBarCloseIcon;

  @FindBy(xpath = "//div[@class='bm-menu']")
  WebElement sideBarMenu;

  @FindBy(xpath = "//a[@id='logout_sidebar_link']")
  WebElement logoutButton;

  // Methods for interacting with elements on the Cart page

  /**
   * Checks if there are products displayed in the cart.
   *
   * @return true if there are products displayed, false otherwise
   */
  public boolean isListProductsDisplayed() {
    return !listProducts.isEmpty();
  }

  /**
   * Gets the list of all products in the cart.
   *
   * @return a list of WebElements representing products in the cart
   */
  public List<WebElement> getListProducts() {
    return listProducts;
  }

  /**
   * Retrieves a specific product from the cart by index.
   *
   * @param index the index of the product in the cart
   * @return the WebElement representing the specified product
   */
  public WebElement getListProduct(int index) {
    return listProducts.get(index);
  }

  /**
   * Gets the list of all product names in the cart.
   *
   * @return a list of WebElements representing product names in the cart
   */
  public List<WebElement> getListProductNames() {
    return listProductNames;
  }

  /**
   * Retrieves a specific product name from the cart by index.
   *
   * @param index the index of the product name in the cart
   * @return the WebElement representing the specified product name
   */
  public WebElement getListProductName(int index) {
    return listProductNames.get(index);
  }

  /**
   * Gets the list of all remove buttons for products in the cart.
   *
   * @return a list of WebElements representing remove buttons
   */
  public List<WebElement> getListProductRemoveButtons() {
    return listProductRemoveButtons;
  }

  /**
   * Retrieves a specific remove button by index.
   *
   * @param index the index of the product's remove button
   * @return the WebElement representing the specified remove button
   */
  public WebElement getListProductRemoveButton(int index) {
    return listProductRemoveButtons.get(index);
  }

  /**
   * Clicks the checkout button to proceed to the checkout process.
   */
  public void clickCheckOutButton() {
    checkOutButton.click();
  }

  /**
   * Clicks the continue shopping button to navigate back to the inventory page.
   */
  public void clickContinueShoppingButton() {
    continueShoppingButton.click();
  }

  /**
   * Checks if the cart page container is displayed.
   *
   * @return true if the cart page container is displayed, false otherwise
   */
  public boolean isCartPageContainerDisplayed() {
    return cartPageContainer.isDisplayed();
  }

  /**
   * Clicks the cart icon in the header.
   */
  public void clickCartIcon() {
    cartIcon.click();
  }

  /**
   * Checks if the cart icon badge displaying item count is visible.
   *
   * @return true if the cart icon badge is visible, false otherwise
   */
  public boolean isCartIconBadgeDisplayed() {
    try {
      return cartIconBadge.isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Retrieves the value displayed on the cart icon badge.
   *
   * @return the text of the cart badge or an error message if the badge is not detected
   */
  public String getCartIconBadgeValue() {
    if (isCartIconBadgeDisplayed()) {
      return cartIconBadge.getText();
    }
    return "ERROR: Cart badge not detected ...";
  }

  /**
   * Clicks the sidebar menu icon to open the sidebar menu.
   */
  public void clickSideBarMenuIcon() {
    sideBarMenuIcon.click();
  }

  /**
   * Checks if the sidebar menu is displayed.
   *
   * @return true if the sidebar menu is displayed, false otherwise
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
   * Clicks the logout button within the sidebar menu.
   */
  public void clickLogoutButton() {
    logoutButton.click();
  }

}
