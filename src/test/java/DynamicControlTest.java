import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.Assert.*;

public class DynamicControlTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String BASE_URL = "https://the-internet.herokuapp.com";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testDynamicControls() {
        SoftAssert softAssert = new SoftAssert();
        driver.get(BASE_URL + "/dynamic_controls");
        driver.findElement(By.cssSelector("#checkbox-example button")).click();
        softAssert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))).getText(), "It's gone!",
                "Ожидаемое сообщение 'It's gone!' не найдено.");
        softAssert.assertTrue(driver.findElements(By.cssSelector("#checkbox-example java.lang.String")).isEmpty(), "Чекбокс всё ещё присутствует на странице!");
        softAssert.assertFalse(driver.findElement(By.cssSelector("#input-example input")).isEnabled(), "Инпут должен быть заблокирован (disabled)!");
        driver.findElement(By.cssSelector("#input-example button")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("message"), "It's enabled!"));
        softAssert.assertTrue(driver.findElement(By.cssSelector("#input-example input")).isEnabled(), "Инпут должен быть активен (enabled)!");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}