package util;

import org.openqa.selenium.*;

import java.io.File;

public class Util {

    public static File doScreenshot(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    }

    public static void doElementVisible(WebDriver driver, By locator){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView()", driver.findElement(locator));
    }

    public static void quantityHandler(WebElement actualCount, int requiredCount, WebElement add, WebElement remove){
        if(Integer.valueOf(actualCount.getText()) > requiredCount){
            remove.click();
        } else {
            add.click();
        }
    }

}
