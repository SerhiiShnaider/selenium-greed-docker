package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePage {

  private final WebDriver driver;

  @FindBy(name = "q")
  private WebElement searchBox;

  @FindBy(className = "r")
  private List<WebElement> searchResults;

  @FindBy(id = "foot")
  private WebElement footer;

  public GooglePage(final WebDriver driver) {
    this.driver = driver;
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    PageFactory.initElements(driver, this);
  }

  public void goTo() {
    this.driver.get("https://www.google.com");
  }

  public void searchFor(String text) {
    this.searchBox.sendKeys(text);
    this.searchBox.submit();
  }

  public List<WebElement> getSearchResults() {
    return searchResults;
  }

  public void printResults() {
    for (WebElement searchResult : searchResults) {
      System.out.println(searchResult.getText());
    }
  }

}