package stepDefinitions;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DragAndDropPage;
import pages.FormPage;

public class Steps {
    WebDriver driver;
    FormPage formPage;
    DragAndDropPage dragPage;

    @Given("I am on the DemoQA Form page")
    public void i_am_on_the_demo_qa_form_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        formPage = new FormPage(driver);
        formPage.open();
    }

    @When("I fill the form with {string}, {string}, {string} and {string}")
    public void i_fill_the_form(String fn, String ln, String email, String phone) {
        formPage.fillForm(fn, ln, email, phone);
    }

    @And("I click the submit button")
    public void i_click_the_submit_button() {
        formPage.submitForm();
    }

    @Then("I should see a success modal")
    public void i_should_see_a_success_modal() {
        Assertions.assertTrue(formPage.waitForModal());
        driver.quit();
    }

    @Given("I am on the DemoQA Droppable page")
    public void i_am_on_the_demo_qa_droppable_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        dragPage = new DragAndDropPage(driver);
        dragPage.open();
    }

    @When("I drag the element to the target")
    public void i_drag_the_element() {
        dragPage.performDragAndDrop();
    }

    @Then("the target box should display {string}")
    public void the_target_box_should_display(String expectedText) {
        String actualText = dragPage.getDroppableText();
        Assertions.assertTrue(actualText.contains(expectedText),
                "Oczekiwano tekstu " + expectedText + ", ale znaleziono: " + actualText);
        driver.quit();
    }
}