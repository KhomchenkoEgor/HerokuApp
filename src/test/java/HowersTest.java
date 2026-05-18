import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class HowersTest {

    private WebDriver driver;
    private Actions actions;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void checkHovers() {

        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/hovers");

        List<WebElement> avatars = driver.findElements(By.cssSelector(".figure"));

        for (int i = 0; i < avatars.size(); i++) {
            avatars = driver.findElements(By.cssSelector(".figure"));
            WebElement avatar = avatars.get(i);
            actions.moveToElement(avatar).perform();
            WebElement name = avatar.findElement(By.tagName("h5"));
            String expectedName = "name: user" + (i + 1);
            softAssert.assertEquals(name.getText(), expectedName,
                    "Ошибка на профиле " + (i + 1) + ": Имя пользователя не совпадает!");
            WebElement link = avatar.findElement(By.tagName("a"));
            link.click();
            softAssert.assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "Not Found");
            driver.navigate().back();
        }

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}