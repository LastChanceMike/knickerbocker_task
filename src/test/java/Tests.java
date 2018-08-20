
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BookPage;
import pages.KnickerbockerMainPage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.io.FileUtils.copyFile;
import static util.Util.doScreenshot;

public class Tests {
    private static WebDriver driver;
    private KnickerbockerMainPage mainPage;
    private File screenshot;

    @BeforeClass
    public static void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Before
    public void before(){
        driver.get("http://knickerbocker-hotel-new-york.nochi.com/?btest=119");
        mainPage = new KnickerbockerMainPage(driver);
    }

    @Test
    public void owerviewTest() throws IOException {
        mainPage.goToOwerviewArea();
        screenshot = doScreenshot(driver);
        String path = "./target/screenshots/" + "owerviewScreenshot.png";
        copyFile(screenshot, new File(path));
        Assert.assertTrue(new File("./target/screenshots/owerviewScreenshot.png").exists());
    }

    @Test
    public void facilitiesTest() throws IOException{
        mainPage.goToFacilitiesArea();
        screenshot = doScreenshot(driver);
        String path = "./target/screenshots/" + "facilitiesScreenshot.png";
        copyFile(screenshot, new File(path));
        Assert.assertTrue(new File("./target/screenshots/facilitiesScreenshot.png").exists());
    }

    @Test
    public void roomsTest() throws IOException{
        mainPage.goToRoomsArea();
        screenshot = doScreenshot(driver);
        String path = "./target/screenshots/" + "roomsScreenshot.png";
        copyFile(screenshot, new File(path));
        Assert.assertTrue(new File("./target/screenshots/roomsScreenshot.png").exists());
    }

    @Test
    public void locationTest() throws IOException{
        mainPage.goToLocationArea();
        screenshot = doScreenshot(driver);
        String path = "./target/screenshots/" + "locationScreenshot.png";
        copyFile(screenshot, new File(path));
        Assert.assertTrue(new File("./target/screenshots/locationScreenshot.png").exists());
    }

    @Test
    public void reviewsTest() throws IOException{
        mainPage.goToReviewsArea();
        screenshot = doScreenshot(driver);
        String path = "./target/screenshots/" + "reviewsScreenshot.png";
        copyFile(screenshot, new File(path));
        Assert.assertTrue(new File("./target/screenshots/reviewsScreenshot.png").exists());
    }

    @Test
    public void countOfPeopleTest(){
        mainPage.setPersons(3, 2, 2, 10);
        Assert.assertEquals("5Гости / 1Номер", mainPage.getPersonsAreaText());
    }

    @Test
    public void bookRoomTest() throws IOException {
        mainPage.setDates("Январь", 2019, 24, 25);
        mainPage.setPersons(3, 2, 2, 10);
        mainPage.findRooms();
        mainPage.bookRoom();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        screenshot = doScreenshot(driver);
        String path = "./target/screenshots/" + "bookPageScreenshot.png";
        copyFile(screenshot, new File(path));
        Assert.assertTrue(new File("./target/screenshots/bookPageScreenshot.png").exists());
    }

    @Test
    public void countOfGuestsTest(){
        mainPage.setDates("Январь", 2019, 24, 25);
        mainPage.setPersons(3, 2, 2, 10);
        mainPage.findRooms();
        BookPage bookPage = mainPage.bookRoom();
        Assert.assertEquals(5, bookPage.getCountOfGuests());
    }

    @Test
    public void startDateTest(){
        mainPage.setDates("Январь", 2019, 24, 25);
        mainPage.setPersons(3, 2, 2, 10);
        mainPage.findRooms();
        BookPage bookPage = mainPage.bookRoom();
        Assert.assertEquals("2019-01-24", bookPage.getStartDate());
    }

    @Test
    public void endDateTest(){
        mainPage.setDates("Январь", 2019, 24, 25);
        mainPage.setPersons(3, 2, 2, 10);
        mainPage.findRooms();
        BookPage bookPage = mainPage.bookRoom();
        Assert.assertEquals("2019-01-25", bookPage.getEndDate());
    }

    @AfterClass
    public static void afterClass(){
        driver.quit();
    }
}
