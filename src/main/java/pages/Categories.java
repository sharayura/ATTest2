package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author Sharapov Yuri
 */
public class Categories {
    private final WebDriver webDriver;
    private final WebDriverWait wait;

    private int itemSum;

    private static final String CATEGORIES_XPATH = "//a[@id='itemc']";
    private static final String CATEGORY_LINK_XPATH = "//a[@id='itemc' and text()='";
    private static final String ITEM_LINK_XPATH = "//div[@class='col-lg-4 col-md-6 mb-4']";
    private static final String ADD_TO_CART_XPATH = "//a[text()='Add to cart']";
    private static final String PRICE_XPATH = "//h3[@class='price-container']";
    private static final String PRODUCT_ADDED_TEXT = "Product added.";


    public Categories(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public int getItemSum() {
        return itemSum;
    }

    public void checkBasket() {
        List<String> categoryList = webDriver.findElements(By.xpath(CATEGORIES_XPATH)).stream()
                .map(WebElement::getText).toList();
        categoryList.forEach(this::addItem);
    }

    public void addItem(String categoryName) {
        int itemNumber = webDriver.findElements(By.xpath(ITEM_LINK_XPATH)).size();
        webDriver.findElement(By.xpath(CATEGORY_LINK_XPATH + categoryName + "']")).click();
        wait.until(d -> itemNumber != webDriver.findElements(By.xpath(ITEM_LINK_XPATH)).size());

        WebElement firstItem = webDriver.findElements(By.xpath(ITEM_LINK_XPATH)).get(0);
        String itemPriceCatText = firstItem.findElement(By.xpath(".//h5")).getText();
        int itemPriceCat = Integer.parseInt(itemPriceCatText.substring(1));
        firstItem.findElement(By.xpath(".//a")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ADD_TO_CART_XPATH)));

        String itemPriceText = webDriver.findElement(By.xpath(PRICE_XPATH)).getText();
        int itemPrice = Integer.parseInt(itemPriceText.substring(1, itemPriceText.indexOf(" ")));
        Assertions.assertEquals(itemPriceCat, itemPrice, "Prices are not equal");

        itemSum += itemPrice;
        webDriver.findElement(By.xpath(ADD_TO_CART_XPATH)).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver.switchTo().alert();
        String message = alert.getText();
        alert.accept();
        Assertions.assertEquals(PRODUCT_ADDED_TEXT, message);

        webDriver.findElement(By.xpath("//a[@id='nava']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CATEGORIES_XPATH)));
    }
}
