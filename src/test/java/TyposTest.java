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

public class TyposTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(invocationCount = 10)
    public void checkTypos() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/typos");
        // Находим все параграфы. На этой странице их два.
        List<WebElement> paragraphs = driver.findElements(By.tagName("p"));
        // Берем текст первого параграфа (индекс 0)
        String actualFirstParagraphText = paragraphs.get(0).getText();
        // Эталонный текст, который должен быть без ошибок
        String expectedFirstText = "This example demonstrates a typo being introduced. It does it randomly on each page load.";
        // Проверяем соответствие
        softAssert.assertEquals(actualFirstParagraphText, expectedFirstText);
        // Берем текст второго параграфа (индекс 1)
        String actualSecondParagraphText = paragraphs.get(1).getText();
        // Эталонный текст, который должен быть без ошибок
        String expectedSecondText = "Sometimes you'll see a typo, other times you won't.";
        // Проверяем соответствие
        softAssert.assertEquals(actualSecondParagraphText, expectedSecondText);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
