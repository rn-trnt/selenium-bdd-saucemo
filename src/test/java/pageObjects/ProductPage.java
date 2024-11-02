package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents the Product page of the application, with methods to interact with product details and actions.
 */
public class ProductPage {

  WebDriver driver;

  /**
   * Initializes the ProductPage with the specified WebDriver.
   *
   * @param driver the WebDriver to interact with the page.
   */
  public ProductPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  // Locators for elements on the Product page
  @FindBy(xpath = "//div[@id='inventory_item_container']")
  WebElement productPageContainer;

  @FindBy(xpath = "//div[@data-test='inventory-item-name']")
  WebElement productName;

  @FindBy(xpath = "//button[@id='add-to-cart']")
  WebElement addToCartButton;

  @FindBy(xpath = "//button[@id='remove']")
  WebElement removeFromCartButton;

  @FindBy(xpath = "//div[@class='inventory_details_img_container']//img")
  WebElement productImage;

  @FindBy(xpath = "//div[@data-test='inventory-item-price']")
  WebElement productPrice;

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

  // Methods for interacting with elements on the Product page

  /**
   * Checks if the product page container is displayed.
   *
   * @return true if the product page container is visible; false otherwise.
   */
  public boolean isProductPageContainerDisplayed() {
    return productPageContainer.isDisplayed();
  }

  /**
   * Retrieves the WebElement for the product name.
   *
   * @return the WebElement of the product name.
   */
  public WebElement getProductName() {
    return productName;
  }

  /**
   * Clicks the button to add the product to the cart.
   */
  public void clickAddToCartButton() {
    addToCartButton.click();
  }

  /**
   * Clicks the button to remove the product from the cart.
   */
  public void clickRemoveFromCartButton() {
    removeFromCartButton.click();
  }

  /**
   * Retrieves the WebElement for the product image.
   *
   * @return the WebElement of the product image.
   */
  public WebElement getProductImage() {
    return productImage;
  }

  /**
   * Retrieves the WebElement for the product price.
   *
   * @return the WebElement of the product price.
   */
  public WebElement getProductPrice() {
    return productPrice;
  }

  /**
   * Clicks the cart icon to navigate to the cart.
   */
  public void clickCartIcon() {
    cartIcon.click();
  }

  /**
   * Checks if the cart icon badge is displayed.
   *
   * @return true if the cart icon badge is visible; false otherwise.
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
   * @return the cart icon badge value as a String or an error message if not detected.
   */
  public String getCartIconBadgeValue() {
    if (isCartIconBadgeDisplayed()) {
      return cartIconBadge.getText();
    }
    return "ERROR: Cart badge not detected ...";
  }

  /**
   * Clicks the sidebar menu icon to open the menu.
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
   * Clicks the logout button within the sidebar menu.
   */
  public void clickLogoutButton() {
    logoutButton.click();
  }
}
