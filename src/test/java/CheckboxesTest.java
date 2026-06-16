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

public class CheckboxesTest {

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
    public void checkCheckboxes() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        // Находим оба чекбокса в список
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        WebElement firstCheckbox = checkboxes.get(0);
        WebElement secondCheckbox = checkboxes.get(1);
        // Проверка первого чекбокса
        // 1. Проверяем, что галочки нет (unchecked)
        softAssert.assertFalse(firstCheckbox.isSelected());
        // 2. Ставим галочку
        firstCheckbox.click();
        // 3. Проверяем, что галочка появилась (checked)
        softAssert.assertTrue(firstCheckbox.isSelected());
        // Проверка второго чекбокса
        // На этой странице второй чекбокс нажат по умолчанию
        softAssert.assertTrue(secondCheckbox.isSelected());
        //4. Снимаем галочку
        secondCheckbox.click();
        softAssert.assertFalse(secondCheckbox.isSelected());
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

