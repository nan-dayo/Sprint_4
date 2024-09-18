package praktikum.scooter;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
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

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;

    private final String firstName;
    private final String secondName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String deliveryDate;
    private final String rentalPeriod;

    public OrderTest(String firstName, String secondName, String address, String metroStation, String phoneNumber, String deliveryDate, String rentalPeriod) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
    }


    @Parameterized.Parameters
    public static Object[][] setUserData() {
        return new Object[][]{
                {"Анна", "Ахматова", "ул. Чайковского, 2", "Сокольники", "+79110123456", "2024-09-22", "сутки"},
                {"Гарри", "Поттер", "ул. Школьная, 15", "Арбатская", "+79116543210", "2024-09-25", "двое суток"}
        };
    }


    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void testUpperOrderButtonFlow() {
        MainPage objMainPage = new MainPage(driver);
        OrderFormPage objOrderForm = new OrderFormPage(driver);

        // Ожидание появления верхней кнопки "Заказать"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[@class = 'Button_Button__ra12g']")));

        // Нажать кнопку "Заказать" вверху страницы
        objMainPage.clickUpperOrderButton();

        // Ожидание загрузки формы заказа
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='Order_Form__17u6u']")));

        // Создание заказа
        objOrderForm.createOrder(firstName, secondName, address, metroStation, phoneNumber, deliveryDate, rentalPeriod);

        // Проверить, что всплывающее окно с сообщением об успешном создании заказа появилось
        By successPopup = By.xpath("//div[contains(text(), 'Заказ оформлен')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(successPopup));

        assertTrue("Сообщение об успешном создании заказа не отображается",
                driver.findElement(successPopup).isDisplayed());

    }



    @Test
    public void testLowerOrderButtonFlow() {
        MainPage objMainPage = new MainPage(driver);
        OrderFormPage objOrderForm = new OrderFormPage(driver);

        // Ожидание появления нижней кнопки "Заказать"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderButton );

        // Нажать кнопку "Заказать" внизу страницы
        objMainPage.clickLowerOrderButton();

        // Создание заказа
        objOrderForm.createOrder(firstName, secondName, address, metroStation, phoneNumber, deliveryDate, rentalPeriod);

        // Проверить, что всплывающее окно с сообщением об успешном создании заказа появилось
        By successPopup = By.xpath("//div[contains(text(), 'Заказ оформлен')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(successPopup));

        assertTrue("Сообщение об успешном создании заказа не отображается",
                driver.findElement(successPopup).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
