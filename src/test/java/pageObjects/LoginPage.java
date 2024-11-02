package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents the Login page of the application, containing methods to interact with the login form elements.
 */
public class LoginPage {

  WebDriver driver;

  /**
   * Initializes the LoginPage with the specified WebDriver.
   *
   * @param driver the WebDriver to interact with the page.
   */
  public LoginPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  // Locators for elements on the Login page
  @FindBy(xpath = "//div[@class='login_container']")
  WebElement loginPageContainer;

  @FindBy(id = "user-name")
  WebElement usernameField;

  @FindBy(id = "password")
  WebElement passwordField;

  @FindBy(id = "login-button")
  WebElement loginButton;

  @FindBy(css = "h3[data-test='error']")
  WebElement errorMessage;

  @FindBy(xpath = "//button[@class='error-button']//*[name()='svg']")
  WebElement errorButton;

  @FindBy(xpath = "//div[@class='login_logo']")
  WebElement logo;

  // Methods for interacting with elements on the Login page

  /**
   * Checks if the login page container is displayed.
   *
   * @return true if the login page container is visible; false otherwise.
   */
  public boolean isLoginPageContainerDisplayed() {
    return loginPageContainer.isDisplayed();
  }

  /**
   * Enters the provided username in the username field.
   *
   * @param username the username to be entered.
   */
  public void enterUsername(String username) {
    usernameField.clear();
    usernameField.sendKeys(username);
  }

  /**
   * Enters the provided password in the password field.
   *
   * @param password the password to be entered.
   */
  public void enterPassword(String password) {
    passwordField.clear();
    passwordField.sendKeys(password);
  }

  /**
   * Clicks the login button.
   */
  public void clickLoginButton() {
    loginButton.click();
  }

  /**
   * Retrieves the error message text displayed on the page.
   *
   * @return the error message as a String.
   */
  public String getErrorMessage() {
    return errorMessage.getText();
  }

  /**
   * Checks if the error message is displayed.
   *
   * @return true if the error message is visible; false otherwise.
   */
  public boolean isErrorMessageDisplayed() {
    try {
      return errorMessage.isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Checks if the logo is displayed on the login page.
   *
   * @return true if the logo is visible; false otherwise.
   */
  public boolean isLogoDisplayed() {
    return logo.isDisplayed();
  }

  /**
   * Clicks the error button to dismiss the error message.
   */
  public void clickErrorButton() {
    errorButton.click();
  }
}
