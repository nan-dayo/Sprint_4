package praktikum.scooter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.scooter.pageObject.MainPage;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.After;
import org.junit.Test;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class DropdownTest {

    private WebDriver driver;

    @Before
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void DropdownOpensTest(){
        MainPage objMainPage = new MainPage(driver);

        // Ожидание видимости и скроллинг к элементу выпадающего списка
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[contains(@class, 'accordion')]")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);


        // Нажать на первый элемент выпадающего списка
        objMainPage.clickDropdownQuestionByIndex(0);
        // Проверить, что текст ответа развернулся
        assertTrue("Ответ на вопрос не отображается", objMainPage.isAnswerDisplayedByIndex(0));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
