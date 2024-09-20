package praktikum.scooter;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {

    protected WebDriver driver;

    @Before
        public void setUp(){
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
            driver.get("https://qa-scooter.praktikum-services.ru/");
        }

    @After
    public void tearDown() {
        driver.quit();
    }

}
