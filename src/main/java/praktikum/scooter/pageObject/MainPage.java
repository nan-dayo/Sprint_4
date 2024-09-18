package praktikum.scooter.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;




public class MainPage {

    private WebDriver driver;

    //Кнопка "Заказать" (вверху страницы)
    private By upperOrderButton = By.xpath(".//button[@class = 'Button_Button__ra12g']");
    // Кнопка "Заказать" (внизу страницы)
    private By lowerOrderButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    // Выпадающий список "Вопросы о важном"
    private By questionsDropdown = By.xpath(".//div[@class='Home_FourPart__1uthg']/div/div/div/div[@class='accordion__heading']");
    // Развернутый текст вопроса из выпадающего списка
    private By questionAnswers = By.className("accordion__panel");


    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickUpperOrderButton(){
        driver.findElement(upperOrderButton).click();
    }

    public void clickLowerOrderButton(){
        driver.findElement(lowerOrderButton).click();
    }

    // Метод для раскрытия конкретного вопроса по индексу (например, первый, второй, и т.д.)
    public void clickDropdownQuestionByIndex(int index) {
        driver.findElements(questionsDropdown).get(index).click();
    }

    // Метод для получения текста ответа на конкретный вопрос по индексу
    public boolean isAnswerDisplayedByIndex(int index) {
        return driver.findElements(questionAnswers).get(index).isDisplayed();
    }

    //////////////////////////////////////////////////////////

    // Логотип Самокат
    private By scooterLogo = By.className("Header_LogoScooter__3lsAR");
    // Логотип Яндекс
    private By yandexLogo = By.className("Header_LogoYandex__3TSOI");
    //Кнопка "Статус заказа"
    private By orderStatusButton = By.xpath(".//button[text()='Статус заказа']");
    // Поле "Введите номер заказа"
    private By orderSearch = By.xpath(".//input[@placeholder='Введите номер заказа']");
    // Кнопка "Go!"
    private By goButton = By.xpath(".//button[text()='Go!']");

    // Метод для клика на логотип Самоката
    public void clickOnScooterLogo(){
        driver.findElement(scooterLogo).click();
    }
    // Метод для клика на логотип Яндекса
    public void clickOnYandexLogo(){
        driver.findElement(yandexLogo).click();
    }
    //Метод для клика на кнопку "Статус заказа"
    public void clickOrderStatusButton(){
        driver.findElement(orderStatusButton).click();
    }
    // Меетод для клика на поле "Введите номер заказа"
    public void clickOnOrderSearchField(){
        driver.findElement(orderSearch).click();
    }
    // Метод для ввода номера заказа
    public void fillInOrderNumber(String orderNumber){
        driver.findElement(orderSearch).sendKeys(orderNumber);
    }
    // Метод для нажатия кнопки "Go!"
    public void clickGoButton(){
        driver.findElement(goButton).click();
    }



}