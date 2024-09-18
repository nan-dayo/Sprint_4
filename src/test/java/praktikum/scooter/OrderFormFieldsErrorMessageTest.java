package praktikum.scooter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.scooter.pageObject.MainPage;
import praktikum.scooter.pageObject.OrderFormPage;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.After;
import org.junit.Test;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class OrderFormFieldsErrorMessageTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkErrorMessagesInOrderFormFields(){
        OrderFormPage objOrderForm = new OrderFormPage(driver);
        MainPage objMainPage = new MainPage(driver);

        objMainPage.clickUpperOrderButton();
        objOrderForm.clickNextButton();

        WebElement firstNameError = driver.findElement(By.xpath(".//div[text()='Введите корректное имя']"));
        assertTrue("Ошибка для поля 'Имя' не появилась",firstNameError.isDisplayed());

        WebElement lastNameError = driver.findElement(By.xpath(".//div[text()='Введите корректную фамилию']"));
        assertTrue("Ошибка для поля 'Фамилия' не появилась",lastNameError.isDisplayed());

        WebElement addressError = driver.findElement(By.xpath(".//div[text()='Введите корректный адрес']"));
        assertTrue("Ошибка для поля 'Адрес' не появилась", addressError.isDisplayed());

        WebElement phoneNumberError = driver.findElement(By.xpath(".//div[text()='Введите корректный номер']"));
        assertTrue("Ошибка для поля 'Телефон' не появилась", phoneNumberError.isDisplayed());

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[text()='Комментарий для курьера']")));

        WebElement commentFieldError = driver.findElement(By.xpath(".//div[text()='Про аренду']"));
        assertTrue("Ошибка для поля 'Комментарий' не появилась", commentFieldError.isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
