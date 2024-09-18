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
import static org.junit.Assert.assertEquals;


public class LogoScooterTest {

    private WebDriver driver;

    @Before
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void testClickingScooterLogoRedirectsToHomePage(){

        MainPage objMainPage = new MainPage(driver);

        // Ожидание, пока логотип станет кликабельным
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Scooter']")));

        // Кликнуть на логотип Самоката
        objMainPage.clickOnScooterLogo();

        // Ожидание изменения URL на ожидаемый
        wait.until(ExpectedConditions.urlToBe("https://qa-scooter.praktikum-services.ru/"));

        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://qa-scooter.praktikum-services.ru/", currentUrl);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
