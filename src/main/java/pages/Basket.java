package pages;

import data.OrderData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Sharapov Yuri
 */
public class Basket {
    private final WebDriver webDriver;
    private final WebDriverWait wait;

    private static final String CART_LINK_XPATH = "//a[@class='nav-link' and text()='Cart']";
    private static final String PLACE_ORDER_XPATH = "//button[text()='Place Order']";
    private static final String TOTAL_PRICE_XPATH = "//h3[@id='totalp']";
    private static final String PURCHASE_BUTTON_XPATH = "//button[text()='Purchase']";
    private static final String PURCHASE_GREETING_XPATH = "//p[contains(., 'Date: ')]";


    public Basket(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public void checkTotalPrice(int itemSum) {
        webDriver.findElement(By.xpath(CART_LINK_XPATH)).click();
        wait.until(ExpectedConditions.textMatches(By.xpath(TOTAL_PRICE_XPATH), Pattern.compile("\\d+")));
        String totalPriceText = webDriver.findElement(By.xpath(TOTAL_PRICE_XPATH)).getText();
        int totalPrice = Integer.parseInt(totalPriceText);
        Assertions.assertEquals(itemSum, totalPrice, "Total price error");
    }

    public void placeOrder() {
        webDriver.findElement(By.xpath(PLACE_ORDER_XPATH)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PURCHASE_BUTTON_XPATH)));

        Arrays.stream(OrderData.values()).forEach(x -> fieldFill(x.getXpath(), x.getData()));
        webDriver.findElement(By.xpath(PURCHASE_BUTTON_XPATH)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PURCHASE_GREETING_XPATH)));
        String text = webDriver.findElement(By.xpath(PURCHASE_GREETING_XPATH)).getText();
        String date = text.split("Date: ")[1];
        String currentDate = LocalDate.now().getDayOfMonth() + "/"
                + LocalDate.now().getMonthValue() + "/"
                + LocalDate.now().getYear();
        Assertions.assertEquals(currentDate, date, "Date error");
    }

    public void fieldFill(String xpath, String data) {
        WebElement field = webDriver.findElement(By.xpath(xpath));
        field.click();
        field.clear();
        field.sendKeys(data);
    }
}
