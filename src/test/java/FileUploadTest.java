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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class FileUploadTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String BASE_URL = "https://the-internet.herokuapp.com";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testFileUpload() {
        driver.get(BASE_URL + "/upload");
        // Создаем временный файл для симуляции загрузки
        Path tempFilePath;
        try {
            tempFilePath = Files.createTempFile("test_upload_", ".txt");
            // Записываем минимальную информацию, чтобы файл не был пустым
            Files.writeString(tempFilePath, "Hello World Automation");
        } catch (IOException e) {
            Assert.fail("Не удалось создать временный файл в директории TMP: " + e.getMessage());
            return;
        }

        File file = tempFilePath.toFile();
        String absolutePath = file.getAbsolutePath();
        WebElement fileInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("file-upload")));
        fileInput.sendKeys(absolutePath);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("file-submit"))).click();
        WebElement uploadedFilesBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files")));
        String displayedName = uploadedFilesBlock.getText().trim();
        assertEquals(displayedName, file.getName(), "Имя файла на странице не совпадает с отправленным!");

        file.delete();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
