package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BookPage {
    private WebDriver driver;

    private By container = By.xpath("//*[@id='container']");
    private By guests = By.xpath("//*[@class='book-page__person js-book-page__person']");
    private By startDate = By.xpath("//*[@class='book-info__blank room-info-blank-mobile']/div[1]/span[2]");
    private By endDate = By.xpath("//*[@class='book-info__blank room-info-blank-mobile']/div[2]/span[2]");

    public BookPage(WebDriver driver) {
        this.driver = driver;
        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(container));
    }

    public int getCountOfGuests(){
        List<WebElement> list = driver.findElements(guests);
        return list.size();
    }

    public String getStartDate(){
        return driver.findElement(startDate).getText();
    }

    public String getEndDate(){
        return driver.findElement(endDate).getText();
    }

}
