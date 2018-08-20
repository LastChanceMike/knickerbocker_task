package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static util.Util.doElementVisible;
import static util.Util.quantityHandler;


public class KnickerbockerMainPage {
    private WebDriver driver;
    private Select select;

    private By overview = By.xpath("//*[@id='container']//ul[@class='nav-list js-nav-menu']/li[1]");
    private By facilities = By.xpath("//*[@id='container']//ul[@class='nav-list js-nav-menu']/li[2]");
    private By rooms = By.xpath("//*[@id='container']//ul[@class='nav-list js-nav-menu']/li[3]");
    private By location = By.xpath("//*[@id='container']//ul[@class='nav-list js-nav-menu']/li[4]");
    private By reviews = By.xpath("//*[@id='container']//ul[@class='nav-list js-nav-menu']/li[5]");
    private By overviewFrame = By.xpath("//*[@id='overview']");
    private By facilitiesFrame = By.xpath("//*[@id='facilities']");
    private By roomsFrame = By.xpath("//*[@id='rooms']");
    private By locationFrame = By.xpath("//*[@id='location']");
    private By reviewsFrame = By.xpath("//*[@id='reviews']");

    private By checkIn = By.xpath("//*[@class='check-input basic-input  js-basic-field js-open-calendar']");
    private By monthPath = By.xpath("//*[@id='container']//table[1]//th[@class='month-name']/div[1]");
    private By yearPath = By.xpath("//*[@id='container']//table[1]//th[@class='month-name']/div[2]");
    private By clickForwardPath = By.xpath("//*[@id='container']//span[@class='next']");
    private By daysPath = By.xpath("//*[@id='container']//table[1]//td/div");

    private By personChooseForm = By.xpath("//*[@class='check-input basic-input js-r-and-g-btn']");
    private By adultsCountPath = By.xpath("//*[@class='js-r-and-g-container r-and-g-container']//div[@class='adults-wrapper']//div[@class='js-count btn-count']");
    private By addAdultsPath = By.xpath("//*[@class='js-r-and-g-container r-and-g-container']//div[@class='adults-wrapper']//div[@class='js-add btn-add']");
    private By removeAdultsPath = By.xpath("//*[@class='js-r-and-g-container r-and-g-container']//div[@class='adults-wrapper']//div[@class='js-remove btn-remove']");
    private By childrenCountPath = By.xpath("//*[@class='js-r-and-g-container r-and-g-container']//div[@class='children-wrapper']//div[@class='js-count btn-count']");
    private By addChildrenPath = By.xpath("//*[@class='js-r-and-g-container r-and-g-container']//div[@class='children-wrapper']//div[@class='js-add btn-add']");
    private By removeChildrenPath = By.xpath("//*[@class='js-r-and-g-container r-and-g-container']//div[@class='children-wrapper']/div[2]/div[1]");
    private By minChildrenAgePath = By.xpath("//*[@class='js-child-age-container child-age-wrapper']//select[1]");
    private By maxChildrenAgePath = By.xpath("//*[@class='js-child-age-container child-age-wrapper']//select[2]");
    private By getPricePath = By.xpath("//*[@class='get-price']//button[1]");

    private By roomButtonPath = By.xpath("//*[@id='hotel-608662']/div[3]/div[2]/div/div/div/div[2]/div[2]/div[4]/button");



    public KnickerbockerMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToOwerviewArea(){
        WebElement owerviewArea = driver.findElement(overview);
        Actions actions = new Actions(driver);
        actions.moveToElement(owerviewArea).click().perform();
        doElementVisible(driver, overviewFrame);
    }

    public void goToFacilitiesArea(){
        WebElement facilitiesArea = driver.findElement(facilities);
        Actions actions = new Actions(driver);
        actions.moveToElement(facilitiesArea).click().perform();
        doElementVisible(driver, facilitiesFrame);
    }

    public void goToRoomsArea(){
        WebElement roomsArea = driver.findElement(rooms);
        Actions actions = new Actions(driver);
        actions.moveToElement(roomsArea).click().perform();
        doElementVisible(driver, roomsFrame);
    }

    public void goToLocationArea(){
        WebElement locationArea = driver.findElement(location);
        Actions actions = new Actions(driver);
        actions.moveToElement(locationArea).click().perform();
        doElementVisible(driver, locationFrame);
    }

    public void goToReviewsArea(){
        WebElement reviewsArea = driver.findElement(reviews);
        Actions actions = new Actions(driver);
        actions.moveToElement(reviewsArea).click().perform();
        doElementVisible(driver, reviewsFrame);
    }

    public void setDates(String dateMonth, int dateYear, int startDate, int endDate){
        (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(checkIn))).click();
        WebElement forward = driver.findElement(clickForwardPath);
        WebElement month = driver.findElement(monthPath);
        WebElement year = driver.findElement(yearPath);
        while (!month.getText().toLowerCase().equals(dateMonth)&&!year.getText().equals(Integer.toString(dateYear))){
            forward.click();
            month = driver.findElement(monthPath);
            year = driver.findElement(yearPath);
        }
        List<WebElement> numbers = driver.findElements(daysPath);
        for (WebElement e: numbers) {
            if (e.getText().equals(String.valueOf(startDate)) || e.getText().equals(String.valueOf(endDate))){
                e.click();
            }
        }
    }

    public String getPersonsAreaText(){
        WebElement personAreaElement = driver.findElement(personChooseForm);
        return personAreaElement.getText();
    }

    public void setPersons(int requiredAdultCount, int requiredChildrenCount, int requiredMinChildAge, int requiredMaxChildAge){
        (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(personChooseForm))).click();
        WebElement actualAdultsCount = driver.findElement(adultsCountPath);
        WebElement addAdults = driver.findElement(addAdultsPath);
        WebElement removeAdults = driver.findElement(removeAdultsPath);
        WebElement actualChildrenCount = driver.findElement(childrenCountPath);
        WebElement addChildren = driver.findElement(addChildrenPath);
        WebElement removeChildren = driver.findElement(removeChildrenPath);

        while (Integer.valueOf(actualAdultsCount.getText()) != requiredAdultCount){
            quantityHandler(actualAdultsCount, requiredAdultCount, addAdults, removeAdults);
            actualAdultsCount = driver.findElement(adultsCountPath);
        }
        while (Integer.valueOf(actualChildrenCount.getText()) != requiredChildrenCount){
            quantityHandler(actualChildrenCount, requiredChildrenCount, addChildren, removeChildren);
            actualChildrenCount = driver.findElement(childrenCountPath);
        }

        select = new Select(driver.findElement(minChildrenAgePath));
        select.selectByValue(String.valueOf(requiredMinChildAge));
        select = new Select(driver.findElement(maxChildrenAgePath));
        select.selectByValue(String.valueOf(requiredMaxChildAge));
    }

    public void findRooms(){
        driver.findElement(getPricePath).click();
        driver.close();
        for(String handler: driver.getWindowHandles()){
            driver.switchTo().window(handler);
        }
    }

    public BookPage bookRoom(){
        driver.findElement(roomButtonPath).click();
        return new BookPage(driver);
    }

}