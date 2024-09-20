package praktikum.scooter.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderFormPage {

    private WebDriver driver;

    // Форма заказа
    private By orderForm = By.xpath(".//div[@class='Order_Form__17u6u']");
    // Поле ввода имени
    private By firstNameField = By.xpath(".//input[@placeholder = '* Имя']");
    // Поле ввода фамилии
    private By lastNameField = By.xpath(".//input[@placeholder = '* Фамилия']");
    // Поле ввода адреса
    private By addressField = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    // Поле ввода станции метро
    private By metroStationField = By.xpath("//input[@placeholder='* Станция метро']");
    // Поле ввода номера телефона
    private By phoneNumberField = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    // Кнопка "Далее"
    private By nextButton = By.xpath("//button[text()='Далее']");
    // Поле ввода даты доставки
    private By deliveryDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Выпадающий список для выбора срока аренды
    private By rentalPeriodDropdown = By.className("Dropdown-control");
    // Кнопка подтверждения заказа
    private By orderSubmitButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    // Кнопка подтверждения диалогового окна
    private By orderConfirmButton = By.xpath(".//button[text()='Да']");
    // Окно "Заказ оформлен"
    private By successfulOrderPopup = By.xpath("//div[contains(text(), 'Заказ оформлен')]");


    public OrderFormPage(WebDriver driver){
        this.driver = driver;
    }

    public By getOrderForm(){
        return orderForm;
    }

    public void setUserName(String username){
        driver.findElement(firstNameField).sendKeys(username);
    }
    public void setUserLastName(String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
    }
    public void setAddress(String address){
        driver.findElement(addressField).sendKeys(address);
    }

    // Метод для выбора станции метро
    public void setMetroStation(String metroStation){
        driver.findElement(metroStationField).click();
        WebElement stationOption = driver.findElement(By.xpath("//div[text()='" + metroStation + "']"));
        stationOption.click();
    }

    public void setPhoneNumber(String phoneNumber){
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }
    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }

    // Метод для установки даты доставки
    public void setDeliveryDate(String date){
        WebElement dateField = driver.findElement(deliveryDateField);
        dateField.click();
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.ENTER); // Закрыть календарь
    }

    // Метод для выбора срока аренды
    public void setRentalPeriod(String period){
        driver.findElement(rentalPeriodDropdown).click();
        String rentalPeriodXPath = String.format(".//div[@class='Dropdown-menu']/div[text()='%s']", period);
        driver.findElement(By.xpath(rentalPeriodXPath)).click();
    }

    public void clickOrderSubmitButton(){
        driver.findElement(orderSubmitButton).click();
    }

    public void clickOrderConfirmButton(){
        driver.findElement(orderConfirmButton).click();
    }

    public void createOrder(String firstName, String lastName, String address, String metroStation, String phoneNumber, String deliveryDate, String rentalPeriod){
        setUserName(firstName);
        setUserLastName(lastName);
        setAddress(address);
        setMetroStation(metroStation);
        setPhoneNumber(phoneNumber);
        clickNextButton();

        setDeliveryDate(deliveryDate);
        setRentalPeriod(rentalPeriod);
        clickOrderSubmitButton();
        clickOrderConfirmButton();
    }

    public By getSuccessfulOrderPopup(){
        return successfulOrderPopup;
    }

}
