Feature: Form submission
  Scenario: Successfully fill and submit the automation practice form
    Given I am on the DemoQA Form page
    When I fill the form with "Jan", "Kowalski", "jan.kowalski@example.com" and "1234567890"
    And I click the submit button
    Then I should see a success modal