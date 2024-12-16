package co.uk.cz.pageobjects;

import co.uk.cz.utils.PageUtil;
import co.uk.cz.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfirmCarDetailsPage {

    private static final Logger logger = LoggerFactory.getLogger(ConfirmCarDetailsPage.class);

    private WebDriver driver;
    private PageUtil pageUtil;

    @FindBy(className = "vyv__number-plate")
    private WebElement regNum;

    @FindBy(css = "h3.title-5")
    private WebElement make;

    @FindBy(css = "h3.title-5+h4")
    private WebElement model;

    @FindBy(xpath = "//*[text()='Confirm your vehicle']")
    private WebElement confirmVehicle;

    @FindBy(xpath = "//*[text()='Not my vehicle']")
    private WebElement notMyVehicle;

    public ConfirmCarDetailsPage() {
        this.driver = WebDriverFactory.getDriver();
        PageFactory.initElements(driver, this);
        pageUtil = new PageUtil(driver);
    }

    public String getRegNumber() {
        return pageUtil.getElementText(regNum);
    }

    public String getMake() {
        return pageUtil.getElementText(make);
    }

    public String getModel() {
        return pageUtil.getElementText(model);
    }

    public String getConfirmVehicle() {
        try {
            return pageUtil.getElementText(confirmVehicle);
        }catch (Exception ex){
            logger.error("No element found:",ex);
            return null;
        }
    }

    public void navigateBackToSearchPage() {
        notMyVehicle.click();
    }


}
