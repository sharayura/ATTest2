package com.demoblaze;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

/**
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



    }
}
