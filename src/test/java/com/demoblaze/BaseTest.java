package com.demoblaze;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @author Sharapov Yuri
 */
public class BaseTest {
    protected WebDriver webDriver;
    protected static WebDriverWait wait;
    protected static final Long TIMEOUT = 30L;
    protected static final String TEST_URL = "https://www.demoblaze.com/";
    protected static final String MAIN_TITLE = "STORE";

    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        chromeOptions.addArguments("start-maximized");

        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
    }

  //  @AfterEach
    public void after() {
        webDriver.quit();
    }
}
