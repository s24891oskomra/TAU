package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormPage extends BasePage {

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(css = "label[for='gender-radio-1']")
    private WebElement maleGenderOption;

    @FindBy(id = "userNumber")
    private WebElement mobileInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement modalTitle;

    public FormPage(WebDriver driver) {
        super(driver);
    }

    public FormPage open() {
        openUrl("https://demoqa.com/automation-practice-form");
        return this;
    }

    public FormPage fillForm(String firstName, String lastName, String email, String phone) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(emailInput, email);
        click(maleGenderOption);
        type(mobileInput, phone);
        return this;
    }

    public void submitForm() {
        scrollIntoView(submitButton);
        click(submitButton);
    }

    public boolean waitForModal() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(modalTitle));
            return modalTitle.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

}
