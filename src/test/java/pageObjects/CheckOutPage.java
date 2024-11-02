package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Represents the Checkout page within the application.
 * Provides methods to interact with elements on the checkout page, such as entering user information,
 * viewing the cart summary, and completing or canceling the checkout process.
 */
public class CheckOutPage {

  WebDriver driver;

  /**
   * Constructor initializes elements on the Checkout page.
   *
   * @param driver the WebDriver instance to interact with the browser
   */
  public CheckOutPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  // Locators for elements on the Checkout page
  @FindBy(xpath = "//div[@id='checkout_info_container']")
  WebElement checkOutPageContainer;

  @FindBy(xpath = "//div[@id='checkout_summary_container']")
  WebElement checkOutSummaryContainer;

  @FindBy(xpath = "//div[@id='checkout_complete_container']")
  WebElement checkOutCompleteContainer;

  @FindBy(xpath = "//input[@id='first-name']")
  WebElement firstNameField;

  @FindBy(xpath = "//input[@id='last-name']")
  WebElement lastNameField;

  @FindBy(xpath = "//input[@id='postal-code']")
  WebElement zipCodeField;

  @FindBy(xpath = "//input[@id='continue']")
  WebElement continueButton;

  @FindBy(xpath = "//button[@id='cancel']")
  WebElement cancelButton;

  @FindBy(xpath = "//button[@id='finish']")
  WebElement finishButton;

  @FindBy(xpath = "//button[@id='back-to-products']")
  WebElement backHomeButton;

  @FindBy(xpath = "//button[@class='error-button']")
  WebElement errorButton;

  @FindBy(xpath = "//h3[@data-test='error']")
  WebElement errorMessage;

  @FindBy(xpath = "//div[@class='cart_item']")
  List<WebElement> cartItems;

  @FindBy(xpath = "//div[@data-test='inventory-item-name']")
  List<WebElement> productNames;

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

  // Methods for interacting with elements on the Checkout page

  /**
   * Checks if the checkout info section is displayed.
   *
   * @return true if the checkout info section is displayed, false otherwise
   */
  public boolean isCheckOutPageContainerDisplayed() {
    try {
      return checkOutPageContainer.isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Checks if the checkout summary section is displayed.
   *
   * @return true if the checkout summary section is displayed, false otherwise
   */
  public boolean isCheckOutSummaryContainerDisplayed(){
    try {
      return checkOutSummaryContainer.isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Checks if the checkout completion container is displayed.
   *
   * @return true if the checkout completion container is displayed, false otherwise
   */
  public boolean isCheckOutCompleteContainerDisplayed(){
    try {
      return checkOutCompleteContainer.isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Enters the first name in the checkout form.
   *
   * @param firstName the first name to enter
   */
  public void enterFirstName(String firstName){
    firstNameField.clear();
    firstNameField.sendKeys(firstName);
  }

  /**
   * Enters the last name in the checkout form.
   *
   * @param lastName the last name to enter
   */
  public void enterLastName(String lastName){
    lastNameField.clear();
    lastNameField.sendKeys(lastName);
  }

  /**
   * Enters the postal code in the checkout form.
   *
   * @param zipCode the postal code to enter
   */
  public void enterZipCode(String zipCode){
    zipCodeField.clear();
    zipCodeField.sendKeys(zipCode);
  }

  /**
   * Clicks the continue button to proceed with checkout.
   */
  public void clickContinueButton(){
    continueButton.click();
  }

  /**
   * Clicks the cancel button to abort the checkout process.
   */
  public void clickCancelButton(){
    cancelButton.click();
  }

  /**
   * Clicks the finish button to complete the checkout process.
   */
  public void clickFinishButton(){
    finishButton.click();
  }

  /**
   * Clicks the back home button to return to the products page after checkout.
   */
  public void clickBackHomeButton(){
    backHomeButton.click();
  }

  /**
   * Clicks the error button if displayed.
   */
  public void clickErrorButton(){
    errorButton.click();
  }

  /**
   * Retrieves the error message displayed during checkout issues.
   *
   * @return the error message text
   */
  public String getErrorMessage(){
    return errorMessage.getText();
  }

  /**
   * Checks if an error message is displayed on the checkout page.
   *
   * @return true if an error message is displayed, false otherwise
   */
  public boolean isErrorMessageDisplayed(){
    try {
      return errorMessage.isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Gets the list of items in the cart summary.
   *
   * @return a list of WebElements representing cart items
   */
  public List<WebElement> getCartItems(){
    return cartItems;
  }

  /**
   * Gets the list of product names in the checkout summary.
   *
   * @return a list of WebElements representing product names
   */
  public List<WebElement> getProductNames(){
    return productNames;
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
