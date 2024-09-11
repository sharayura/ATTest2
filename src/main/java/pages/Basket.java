package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Sharapov Yuri
 */
public class Basket {
    private WebDriver webDriver;
    private WebDriverWait wait;

    public Basket(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }
}
