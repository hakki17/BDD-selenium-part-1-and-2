package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GeolocationPage {

    private WebDriver driver;

    // PageFactory mapea este campo al elemento con id="content" automáticamente
    @FindBy(id = "content")
    private WebElement content;

    @FindBy(css = "button")
    private WebElement whereAmIButton;

    public GeolocationPage(WebDriver driver) {
        this.driver = driver;
        // Esta línea "inyecta" los elementos anotados con @FindBy
        PageFactory.initElements(driver, this);
    }

    public void navigateTo() {
        driver.get("https://the-internet.herokuapp.com/geolocation");
    }

    public void clickWhereAmI() {
        whereAmIButton.click();
    }

    public boolean latitudeIsDisplayed() {
        return content.getText().contains("Latitude");
    }

    public boolean longitudeIsDisplayed() {
        return content.getText().contains("Longitude");
    }
}