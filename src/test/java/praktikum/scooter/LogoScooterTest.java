package praktikum.scooter;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.scooter.pageObject.MainPage;
import org.junit.Test;
import java.time.Duration;
import static org.junit.Assert.assertEquals;


public class LogoScooterTest extends BaseTest{

    @Test
    public void testClickingScooterLogoRedirectsToHomePage(){

        MainPage objMainPage = new MainPage(driver);

        // Ожидание, пока логотип станет кликабельным
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(objMainPage.getScooterLogo()));

        // Кликнуть на логотип Самоката
        objMainPage.clickOnScooterLogo();

        // Ожидание изменения URL на ожидаемый
        wait.until(ExpectedConditions.urlToBe("https://qa-scooter.praktikum-services.ru/"));

        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://qa-scooter.praktikum-services.ru/", currentUrl);

    }

}
