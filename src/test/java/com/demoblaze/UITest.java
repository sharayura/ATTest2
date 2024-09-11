package com.demoblaze;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Basket;
import pages.Categories;
import pages.MainPage;

/**
 * Класс запуска тестов
 * @author Sharapov Yuri
 */
public class UITest extends BaseTest {

    @Test
    public void case1Test() {
        webDriver.get(TEST_URL);
        wait = new WebDriverWait(webDriver, TIMEOUT);
        wait.until(ExpectedConditions.titleContains(MAIN_TITLE));

        MainPage mainPage = new MainPage(webDriver, wait);
        mainPage.register();
        mainPage.login();

        Categories categories = new Categories(webDriver, wait);
        categories.checkBasket();

        Basket basket = new Basket(webDriver, wait);
        basket.checkTotalPrice(categories.getItemSum());
        basket.placeOrder();

    }
}
