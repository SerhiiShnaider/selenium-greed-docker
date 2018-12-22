import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.GooglePage;

public class GoogleTest {

  private WebDriver chromeDriver;
  private WebDriver firefoxDriver;
  private GooglePage google;

  @BeforeTest
  public void setUp() throws MalformedURLException {
    DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
    DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();

    chromeDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeCapabilities);
    firefoxDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
        firefoxCapabilities);

  }

  @Test
  public void chromeGoogleTest() {
    System.out.println("----  CHROME ----\n");

    google = new GooglePage(chromeDriver);
    google.goTo();
    google.searchFor("automation");
    Assert.assertTrue(google.getSearchResults().size() >= 5);
    google.printResults();
  }

  @Test
  public void firefoxGoogleTest() {
    System.out.println("----  FIREFOX ----\n");

    google = new GooglePage(firefoxDriver);
    google.goTo();
    google.searchFor("automation");

    Assert.assertTrue(google.getSearchResults().size() >= 5);
    google.printResults();
  }

  @AfterTest
  public void tearDown() {
    chromeDriver.quit();
    firefoxDriver.quit();
  }

}
