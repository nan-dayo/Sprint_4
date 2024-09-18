package praktikum.scooter;

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

public class IncorrectOrderNumberInputTest {

    private WebDriver driver;

    @Before
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void testIncorrectOrderNumberInputShowsNoOrderMessage() {

        MainPage objMainPage = new MainPage(driver);

        // Нажимаем на кнопку "Статус заказа"
        objMainPage.clickOrderStatusButton();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//input[@placeholder='Введите номер заказа']"))));

        // Кликаем на поле для ввода номера заказа
        objMainPage.clickOnOrderSearchField();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(".//input[@placeholder='Введите номер заказа']"))));

        // Вводим неверный номер заказа
        objMainPage.fillInOrderNumber("000000");
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@placeholder='Введите номер заказа']"), "000000"));

        // Нажимаем на кнопку "Go"
        objMainPage.clickGoButton();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//img[@alt='Not found']")));

        // Проверка, что сообщение о неверном номере заказа появилось
        WebElement noOrderMessage = driver.findElement(By.xpath(".//img[@alt='Not found']"));
        assertTrue("Не появилось сообщение о неверном номере заказа", noOrderMessage.isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
