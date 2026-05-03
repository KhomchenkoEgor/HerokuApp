import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.time.Duration;

public class NotificationMessagesTest {

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
    public void checkNotificationMessage() {
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/p/a")).click();
        // 1. Находим элемент сообщения
        // 2. Проверяем, что сообщение содержит нужный текст
        // 3. Используем contains, так как внутри блока есть еще текст кнопки "×" и пробелы
        String messageText = driver.findElement(By.id("flash")).getText();
        boolean isSuccess = messageText.contains("Action successful");
        boolean isUnsuccessful = messageText.contains("Action unsuccesful, please try again");
        // Тест пройдет, если верно хотя бы одно из условий
        Assert.assertTrue(isSuccess || isUnsuccessful);
        System.out.println("Выпавшее сообщение: " + messageText);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}


