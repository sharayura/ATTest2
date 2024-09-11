package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

/**
 * @author Sharapov Yuri
 */
public class MainPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    private String login;
    private static final String PASSWORD = "1";

    private static final String SIGNUP_MENU_XPATH = "//a[@id='signin2']";
    private static final String SIGNUP_USERNAME_XPATH = "//input[@id='sign-username']";
    private static final String SIGNUP_PASSWORD_XPATH = "//input[@id='sign-password']";
    private static final String SIGNUP_BUTTON_XPATH = "//button[text()='Sign up']";

    public MainPage(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public void register() {
        webDriver.findElement(By.xpath(SIGNUP_MENU_XPATH)).click();
        WebElement userField = webDriver.findElement(By.xpath(SIGNUP_USERNAME_XPATH));
        userField.click();
        login = loginGen();
        userField.sendKeys(login);
        WebElement passField = webDriver.findElement(By.xpath(SIGNUP_PASSWORD_XPATH));
        passField.click();
        passField.sendKeys(PASSWORD);
        webDriver.findElement(By.xpath(SIGNUP_BUTTON_XPATH)).click();

        String message = webDriver.switchTo().alert().getText();
        System.out.println(message);
        //webDriver.switchTo().alert().accept();


    }

    public String loginGen() {
        Random random = new Random();
        int number = random.nextInt(10000);
        return "newuser" + number;
    }
}
