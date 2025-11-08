package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.FormPage;

public class FormTest extends BaseTest {

    @Test
    void shouldFillAndSubmitForm() {
        FormPage formPage = new FormPage(driver)
                .open()
                .fillForm("Jan", "Kowalski", "jan.kowalski@example.com", "1234567890");

        formPage.submitForm();

        Assertions.assertTrue(formPage.waitForModal(),
                "Modal powinien być widoczny po wysłaniu formularza");
    }
}
