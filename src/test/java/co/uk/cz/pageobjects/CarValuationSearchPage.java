package co.uk.cz.pageobjects;

import co.uk.cz.utils.PageUtil;
import co.uk.cz.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class CarValuationSearchPage {

    private static final Logger logger = LoggerFactory.getLogger(CarValuationSearchPage.class);

    private WebDriver driver;

    @FindBy(name = "vrm")
    private WebElement vehicleReg;

    @FindBy(name = "mileage")
    private WebElement mileage;

    @FindBy(name = "postcode")
    private WebElement postCode;

    @FindBy(className = "vyv-form__input--submit")
    private WebElement valueMyCar;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookies;

    @FindBy(className = "err__msg-box")
    private WebElement vehicleNotFound;

    public CarValuationSearchPage() {
        this.driver = WebDriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public String getVehicleNotFoundMsg(){
        return new PageUtil(driver).getElementText(vehicleNotFound);
    }


    public void navigateToCazooWebsite(){
        driver.navigate().to("https://www.cazoo.co.uk/value-my-car/");
        acceptCookies();
    }


    public void enterCarDetails(String regNum) throws Exception{
        WaitTillElementPresent(vehicleReg);
        vehicleReg.clear();
        vehicleReg.sendKeys(regNum);

        WaitTillElementPresent(mileage);
        mileage.clear();
        String randomMileage = String.valueOf(ThreadLocalRandom.current().nextInt(5000, 200000));
        mileage.sendKeys(randomMileage);

        WaitTillElementPresent(postCode);
        postCode.clear();
        postCode.sendKeys("RG40 5AT");

        valueMyCar.click();
        Thread.sleep(2000);
    }

    private void acceptCookies() {
        WaitTillElementPresent(acceptCookies);
        acceptCookies.click();
    }

    private void WaitTillElementPresent(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

}
