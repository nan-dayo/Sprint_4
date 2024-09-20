package praktikum.scooter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.scooter.pageObject.MainPage;
import praktikum.scooter.pageObject.OrderFormPage;
import org.openqa.selenium.By;
import org.junit.Test;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class OrderFormFieldsErrorMessageTest extends BaseTest{

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

        objOrderForm.clickNextButton();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[text()='Комментарий для курьера']")));

        WebElement commentFieldError = driver.findElement(By.xpath(".//div[text()='Про аренду']"));
        assertTrue("Ошибка для поля 'Комментарий' не появилась", commentFieldError.isDisplayed());
    }

}
