package seleniumE2E;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AddToCart_Checkout {
  public WebDriver driver;
  private String username = "Rex.Jones";
  private String accessKey = "";
  private String hub = "@hub.lambdatest.com/wd/hub";
  DesiredCapabilities caps = new DesiredCapabilities();

  //  By myAccountMenu = By.xpath("//div[@id='widget-navbar-217834']//span[contains(text(),'My account')]");
  By myAccountMenu = By.xpath("//div[@id='entry_217834']//span[contains(text(),'My account')]");
  By email = By.id("input-email");
  By password = By.id("input-password");

  By loginButton = By.xpath("//input[@type='submit' and @value='Login']");
  By searchField = By.xpath("//div[@id='entry_217822']//input[@aria-label='Search For Products']");
  By searchButton = By.xpath("//div[@id='search']//button[text()='Search']");
  By iPhone = By.xpath("//div[@id='entry_212469']//a[text()='iPhone']");
  By addToCartButton = By.xpath("//div[@id='entry_216842']//button[text()='Add to Cart']");
  By checkoutButton = By.xpath("//div[@id='notification-box-top']//a[contains(text(),'Checkout')]");
  By totalAmount = By.xpath("//div[@id='checkout-cart']/table/tbody/tr/td[4]");

  @Parameters(value={"Browser", "Version", "Platform"})
  @BeforeMethod
  public void setUp(String browser, String version, String platform) {
    caps.setCapability("build", "1.0");
    caps.setCapability("name", "Selenium E2E");
    caps.setCapability("platform", platform);
    caps.setCapability("version", version);
    caps.setCapability("browserName", browser);
    caps.setCapability("network", true);
    caps.setCapability("console", true);
    caps.setCapability("visual", true);
    caps.setCapability("video", true);

    try {
      driver = new RemoteWebDriver(new URL("https://" + username + ":" +
              accessKey + hub), caps);
    } catch(MalformedURLException exc) {
      exc.printStackTrace();
    }
    driver.get("https://ecommerce-playground.lambdatest.io/");
  }

  @Test
  public void test2_AddToCart_Checkout() {
    click(myAccountMenu);
    type(email, "RexAllenJones@GMail.com");
    type(password, "3400Success");
    click(loginButton);
    type(searchField, "iPhone");
    click(searchButton);
    click(iPhone);
    click(addToCartButton);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
    click(checkoutButton);
  }

  private WebElement find(By locator) {
    return driver.findElement(locator);
  }

  private void click(By locator) {
    find(locator).click();
  }

  private void type(By locator, String text) {
    find(locator).sendKeys(text);
  }
}
