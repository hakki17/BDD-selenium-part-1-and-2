package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v145.emulation.Emulation;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import pages.GeolocationPage;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class GeolocationSteps {

    private WebDriver driver;
    private GeolocationPage geolocationPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/local/bin/chrome");
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Inyectamos coordenadas falsas via CDP (Bogotá, Colombia)
        ChromeDriver chromeDriver = (ChromeDriver) driver;
        DevTools devTools = chromeDriver.getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(
            Optional.of((Number) 4.711),
            Optional.of((Number)(-74.0721)),
            Optional.of((Number) 1),         // accuracy
            Optional.empty(),       // altitude
            Optional.empty(),       // altitudeAccuracy
            Optional.empty(),       // heading
            Optional.empty()        // speed
        ));

        geolocationPage = new GeolocationPage(driver);
    }

    @Given("I am on the Geolocation page")
    public void i_am_on_the_geolocation_page() {
        geolocationPage.navigateTo();
    }

    @When("I click the {string} button")
    public void i_click_the_button(String buttonName) {
        geolocationPage.clickWhereAmI();
    }

    @Then("I should see my latitude on the screen")
    public void i_should_see_my_latitude() {
        // Esperamos hasta 5 segundos a que aparezca la latitud
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
            By.id("content"), "Latitude"));
        assert geolocationPage.latitudeIsDisplayed()
            : "Latitude was not displayed on screen";
    }

    @Then("I should see my longitude on the screen")
    public void i_should_see_my_longitude() {
        assert geolocationPage.longitudeIsDisplayed()
            : "Longitude was not displayed on screen";
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}