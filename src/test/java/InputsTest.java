import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.time.Duration;

public class InputsTest {

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
    public void testInputField() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement input = driver.findElement(By.tagName("input"));
        // 1. Проверка буквенного значения
        input.sendKeys("abc");
        Assert.assertEquals(input.getAttribute("value"), "");
        // 2. Очистка и проверка ввода цифрового значения
        input.clear();
        input.sendKeys("10");
        Assert.assertEquals(input.getAttribute("value"), "10");
        // 3. Проверка Keys.ARROW_UP (увеличение на 1)
        input.sendKeys(Keys.ARROW_UP);
        Assert.assertEquals(input.getAttribute("value"), "11");
        // 4. Проверка Keys.ARROW_DOWN (уменьшение на 1)
        input.sendKeys(Keys.ARROW_DOWN);
        Assert.assertEquals(input.getAttribute("value"), "10");
        // 5. Очистка и проверка отрицательных чисел стрелками
        input.clear();
        input.sendKeys("0");
        input.sendKeys(Keys.ARROW_DOWN);
        Assert.assertEquals(input.getAttribute("value"), "-1");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
