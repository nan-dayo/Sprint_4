package praktikum.scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.scooter.pageObject.MainPage;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.After;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class YandexLogoTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void clickOnYandexLogoRedirectsToYandexPage() {

        MainPage objMainPage = new MainPage(driver);

        // Ожидание загрузки страницы Яндекса
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[@class='Header_LogoYandex__3TSOI']")));

        // Нажать на логотип Яндекса
        objMainPage.clickOnYandexLogo();

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Проверка, что открылся сайт Яндекса
        String currentUrl = driver.getCurrentUrl();
        assertTrue("Текущий URL не содержит Яндекс", currentUrl.contains("yandex"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
