package praktikum.scooter;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.scooter.pageObject.MainPage;
import org.junit.Test;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class DropdownTest extends BaseTest {

    private final int questionIndex;

    public DropdownTest(int questionIndex){
        this.questionIndex = questionIndex;
    }

    @Parameterized.Parameters
    public static Object[] setUserData() {
        return new Object[]{0, 1, 2, 3, 4, 5, 6, 7};
    }

    @Test
    public void dropdownOpensTest() {
        MainPage objMainPage = new MainPage(driver);

        // Ожидание видимости и скроллинг к элементу выпадающего списка
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(objMainPage.getQuestionsDropdown()));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        // Нажать на элемент выпадающего списка
        objMainPage.clickDropdownQuestionByIndex(questionIndex);
        // Проверить, что текст ответа развернулся
        assertTrue("Ответ на вопрос не отображается", objMainPage.isAnswerDisplayedByIndex(questionIndex));
    }

}
