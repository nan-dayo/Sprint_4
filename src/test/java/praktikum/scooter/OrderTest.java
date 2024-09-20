package praktikum.scooter;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.scooter.pageObject.MainPage;
import praktikum.scooter.pageObject.OrderFormPage;
import org.openqa.selenium.By;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest{

    private final String firstName;
    private final String secondName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String deliveryDate;
    private final String rentalPeriod;
    private boolean isUpperButton;

    public OrderTest(String firstName, String secondName, String address, String metroStation, String phoneNumber, String deliveryDate, String rentalPeriod, boolean isUpperButton) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.isUpperButton = isUpperButton;
    }

    @Parameterized.Parameters
    public static Object[][] setUserData() {
        return new Object[][]{
                {"Анна", "Ахматова", "ул. Чайковского, 2", "Сокольники", "+79110123456", "2024-09-22", "сутки", true},
                {"Дин", "Винчестер", "ул. Тверская, 13", "Спортивная", "+79220123456", "2024-09-27", "трое суток", true},
                {"Гарри", "Поттер", "ул. Школьная, 15", "Арбатская", "+79116543210", "2024-09-25", "двое суток", false},
                {"Сэм", "Винчестер", "ул. Донская, 37", "Академическая", "+79336543210", "2024-09-24", "четверо суток", false}
        };
    }

    @Test
    public void testUpperOrderButtonFlow() {
        MainPage objMainPage = new MainPage(driver);
        OrderFormPage objOrderForm = new OrderFormPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        if(isUpperButton) {
            wait.until(ExpectedConditions.elementToBeClickable(objMainPage.getUpperOrderButton()));
            objMainPage.clickUpperOrderButton();
        } else {
            WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(objMainPage.getLowerOrderButton()));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderButton );
            objMainPage.clickLowerOrderButton();
        }

        // Ожидание загрузки формы заказа
        wait.until(ExpectedConditions.visibilityOfElementLocated(objOrderForm.getOrderForm()));
        // Создание заказа
        objOrderForm.createOrder(firstName, secondName, address, metroStation, phoneNumber, deliveryDate, rentalPeriod);

        // Проверить, что всплывающее окно с сообщением об успешном создании заказа появилось
        By successPopup = objOrderForm.getSuccessfulOrderPopup();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successPopup));

        assertTrue("Сообщение об успешном создании заказа не отображается",
                driver.findElement(successPopup).isDisplayed());

    }

}
