package co.uk.cz.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class PageUtil {

    private static final Logger logger = LoggerFactory.getLogger(PageUtil.class);

    private WebDriver driver;
    private static final int TIME_OUT = 10;

    public PageUtil(WebDriver driver){
        this.driver = driver;
    }

    public String getElementText(WebElement webElement) {
        try {
            waitTillElementPresent(driver,webElement);
            return webElement.getText();
        } catch (NoSuchElementException ex) {
            logger.error("Exception while while retrieving text:",ex);
            throw ex;
        }
    }

    private void waitTillElementPresent(WebDriver driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
}

