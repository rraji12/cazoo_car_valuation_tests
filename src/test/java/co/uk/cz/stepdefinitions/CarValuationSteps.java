package co.uk.cz.stepdefinitions;

import co.uk.cz.model.CarDetails;
import co.uk.cz.pageobjects.CarValuationSearchPage;
import co.uk.cz.pageobjects.ConfirmCarDetailsPage;
import co.uk.cz.utils.CarValuationUtil;
import co.uk.cz.utils.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class CarValuationSteps {

    private static final Logger logger = LoggerFactory.getLogger(CarValuationSteps.class);

    private CarValuationSearchPage searchPage;
    private ConfirmCarDetailsPage confirmPage;
    private List<String> regNumbers;
    private Map<String, CarDetails>  carDetailsMap;

    public CarValuationSteps(CarValuationSearchPage searchPage, ConfirmCarDetailsPage confirmPage){
        this.searchPage = searchPage;
        this.confirmPage = confirmPage;
    }

    @Given("I have list of car registration numbers in {string}")
    public void i_have_list_of_car_registration_numbers_in(String inputFile) throws Exception{
        regNumbers = CarValuationUtil.getRegistrationNumbersFromInputFile(inputFile);
    }

    @Given("the expected car details are available in {string}")
    public void the_expected_car_details_are_available_in(String outputFile) {
        carDetailsMap = CarValuationUtil.getCarDetailsFromOutputFile(outputFile);
    }

    @When("I retrieve the car details from the carzoo website")
    public void i_retrieve_the_car_details_from_the_carzoo_website() {
        searchPage.navigateToCazooWebsite();
    }

    @Then("Actual vehicle details should match the expected details")
    public void the_actual_vehicle_details_should_match_the_expected_details() throws Exception {
        logger.info("Validating vehicle details...");
        for (String regNum : regNumbers) {
            searchPage.enterCarDetails(regNum);
            String confirmVehicleText = confirmPage.getConfirmVehicle();
            if(confirmVehicleText != null){
                CarDetails carDetails = carDetailsMap.get(regNum);
                Assert.assertEquals("Reg Number Mismatch",carDetails.getRegistration(),confirmPage.getRegNumber());
                Assert.assertEquals("Make Mismatch",carDetails.getMake(),confirmPage.getMake());
                Assert.assertEquals("Model Mismatch",carDetails.getModel(),confirmPage.getModel());
                confirmPage.navigateBackToSearchPage();
            }else{
                String notFoundErrorMsg = searchPage.getVehicleNotFoundMsg();
                Assert.assertEquals("Error Message", "Vehicle could not be found, please update the details of your vehicle or re-enter your registration number above.",
                        notFoundErrorMsg);
            }

        }

    }

    @After
    public void cleanUp(){
        WebDriverFactory.getDriver().quit();
    }

}
