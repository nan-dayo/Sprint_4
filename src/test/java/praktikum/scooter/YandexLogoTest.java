package praktikum.scooter;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.scooter.pageObject.MainPage;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class YandexLogoTest extends BaseTest{ ;

    @Test
    public void clickOnYandexLogoRedirectsToYandexPage() {

        MainPage objMainPage = new MainPage(driver);

        // Ожидание загрузки страницы Яндекса
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(objMainPage.getYandexLogo()));

        // Нажать на логотип Яндекса
        objMainPage.clickOnYandexLogo();

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Проверка, что открылся сайт Яндекса
        String currentUrl = driver.getCurrentUrl();
        assertTrue("Текущий URL не содержит Яндекс", currentUrl.contains("yandex"));
    }

}
