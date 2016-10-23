package readinglist;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=ReadingListApplication.class)
@WebIntegrationTest(randomPort=true)
public class ServerWebTests {
  
  private static FirefoxDriver browser;
  
  @Value("${local.server.port}")
  private int port;

  @BeforeClass
  public static void openBrowser() {
//    DesiredCapabilities capability = DesiredCapabilities.firefox();
//    capability.setCapability("marionette", true);
//    browser = new FirefoxDriver(capability);
//    browser = new FirefoxDriver();






//    browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  
  @Test
  public void addBookToEmptyList() {
    String baseUrl = "http://localhost:" + port;
    browser.get(baseUrl);

    String currentUrl = browser.getCurrentUrl();
    assertEquals(baseUrl +"/readingList", currentUrl);

    assertEquals("You have no books in your book list", 
                 browser.findElementByTagName("div").getText());

    browser.findElementByName("title").sendKeys("BOOK TITLE");
    browser.findElementByName("author").sendKeys("BOOK AUTHOR");
    browser.findElementByName("isbn").sendKeys("1234567890");
    browser.findElementByName("description").sendKeys("DESCRIPTION");
    browser.findElementByTagName("form").submit();
    
    WebElement dl = 
        browser.findElementByCssSelector("dt.bookHeadline");
    assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)", 
                 dl.getText());
    WebElement dt = 
        browser.findElementByCssSelector("dd.bookDescription");
    assertEquals("DESCRIPTION", dt.getText());
  }

  @Test
  public void testOpenGoogle() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "/home/lisandro/dev/tools/chromedriver");
    WebDriver driver = new ChromeDriver();
    driver.get("http://www.google.com");





    Thread.sleep(5000);  // Let the user actually see something!
    WebElement searchBox = driver.findElement(By.name("q"));
    searchBox.sendKeys("ChromeDriver");
    searchBox.submit();
    Thread.sleep(5000);  // Let the user actually see something!
    driver.quit();
  }
  
  @AfterClass
  public static void closeBrowser() {

//    browser.quit();
  }

}
