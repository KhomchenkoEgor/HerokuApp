import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class AddAndRemoveElementsTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkAddAndRemoveElements() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        int countDelete = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        softAssert.assertEquals(countDelete, 2);
        // 1. Получаем список всех кнопок Delete
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//button[text()='Delete']"));
        // 2. Нажимаем на вторую (индекс 1)
        deleteButtons.get(1).click();
        // 3. Проверяем, что осталась только одна кнопка (удаляем вторую)
        int countAfterDelete = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        softAssert.assertEquals(countAfterDelete, 1);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
