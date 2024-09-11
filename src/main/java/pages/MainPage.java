package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

/**
 * @author Sharapov Yuri
 */
public class MainPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    private String login;
    private static final String PASSWORD = "1";
    private static final String SIGNUP_TEXT = "Sign up successful.";

    private static final String SIGNUP_MENU_XPATH = "//a[@id='signin2']";
    private static final String SIGNUP_USERNAME_XPATH = "//input[@id='sign-username']";
    private static final String SIGNUP_PASSWORD_XPATH = "//input[@id='sign-password']";
    private static final String SIGNUP_BUTTON_XPATH = "//button[text()='Sign up']";
    private static final String LOGIN_MENU_XPATH = "//a[@id='login2']";
    private static final String LOGIN_USERNAME_XPATH = "//input[@id='loginusername']";
    private static final String LOGIN_PASSWORD_XPATH = "//input[@id='loginpassword']";
    private static final String LOGIN_BUTTON_XPATH = "//button[text()='Log in']";
    private static final String USER_NAME_XPATH = "//a[@id='nameofuser']";


    public MainPage(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public void register() {
        webDriver.findElement(By.xpath(SIGNUP_MENU_XPATH)).click();
        WebElement passField = webDriver.findElement(By.xpath(SIGNUP_PASSWORD_XPATH));
        passField.click();
        passField.clear();
        passField.sendKeys(PASSWORD);
        String message;
        do {
            WebElement userField = webDriver.findElement(By.xpath(SIGNUP_USERNAME_XPATH));
            userField.click();
            userField.clear();
            login = loginGen();
            userField.sendKeys(login);
            webDriver.findElement(By.xpath(SIGNUP_BUTTON_XPATH)).click();

            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = webDriver.switchTo().alert();
            message = alert.getText();
            alert.accept();
        } while (!message.equals(SIGNUP_TEXT));
    }

    /**
     * Генерация случайного логина
     * @return случайный логин
     */
    public String loginGen() {
        Random random = new Random();
        int number = random.nextInt(10000);
        return "newuser" + number;
    }

    public void login() {
        webDriver.findElement(By.xpath(LOGIN_MENU_XPATH)).click();
        WebElement userField = webDriver.findElement(By.xpath(LOGIN_USERNAME_XPATH));
        userField.click();
        userField.clear();
        userField.sendKeys(login);
        WebElement passField = webDriver.findElement(By.xpath(LOGIN_PASSWORD_XPATH));
        passField.click();
        passField.clear();
        passField.sendKeys(PASSWORD);
        webDriver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();

        wait.until(d -> !webDriver.findElement(By.xpath(USER_NAME_XPATH)).getText().isEmpty());
        String userNameText = webDriver.findElement(By.xpath(USER_NAME_XPATH)).getText();

        Assertions.assertTrue(userNameText.contains(login), "Wrong user name");
    }
}
