import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SortableDataTablesTest {

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
    public void checkTableCells() {
        driver.get("https://the-internet.herokuapp.com/tables");
        // Проверяем 5 разных ячеек в первой таблице (Example 1)
        // 1. Фамилия в 1-м ряду
        String lastNameFirst = driver.findElement(By.xpath("//table[@id='table1']//tr[1]/td[1]")).getText();
        Assert.assertEquals(lastNameFirst, "Smith", "Ошибка в ячейке [1,1]");
        // 2. Имя во 2-м ряду
        String firstNameSecond = driver.findElement(By.xpath("//table[@id='table1']//tbody/tr[2]/td[2]")).getText();
        Assert.assertEquals(firstNameSecond, "Frank", "Ошибка в ячейке [2,2]");
        // 3. Email в 3-м ряду
        String emailThird = driver.findElement(By.xpath("//table[@id='table1']//tbody/tr[3]/td[3]")).getText();
        Assert.assertEquals(emailThird, "jdoe@hotmail.com", "Ошибка в ячейке [3,3]");
        // 4. Вознаграждение (due) в 4-м ряду
        String dueFourth = driver.findElement(By.xpath("//table[@id='table1']//tbody/tr[4]/td[4]")).getText();
        Assert.assertEquals(dueFourth, "$50.00", "Ошибка в ячейке [4,4]");
        // 5. Сайт в 1-м ряду
        String webSiteFirst = driver.findElement(By.xpath("//table[@id='table1']//tbody/tr[1]/td[5]")).getText();
        Assert.assertEquals(webSiteFirst, "http://www.jsmith.com", "Ошибка в ячейке [1,5]");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
