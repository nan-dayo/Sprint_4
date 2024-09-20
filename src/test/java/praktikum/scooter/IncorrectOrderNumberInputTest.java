package praktikum.scooter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.scooter.pageObject.MainPage;
import org.junit.Test;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class IncorrectOrderNumberInputTest extends BaseTest {

    @Test
    public void testIncorrectOrderNumberInputShowsNoOrderMessage() {

        MainPage objMainPage = new MainPage(driver);

        // Нажимаем на кнопку "Статус заказа"
        objMainPage.clickOrderStatusButton();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(objMainPage.getOrderStatusButton())));
        objMainPage.clickOrderStatusButton();

        // Кликаем на поле для ввода номера заказа
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(objMainPage.getOrderSearchField())));
        objMainPage.clickOnOrderSearchField();

        // Вводим неверный номер заказа
        objMainPage.fillInOrderNumber("000000");
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.textToBePresentInElementValue(objMainPage.getOrderSearchField(), "000000"));

        // Нажимаем на кнопку "Go"
        objMainPage.clickGoButton();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(objMainPage.getOrderNotFoundImg()));

        // Проверка, что сообщение о неверном номере заказа появилось
        WebElement noOrderMessage = driver.findElement(objMainPage.getOrderNotFoundImg());
        assertTrue("Не появилось сообщение о неверном номере заказа", noOrderMessage.isDisplayed());
    }


}
