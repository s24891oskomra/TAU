Feature: Drag and Drop
  Scenario: Drag element to the target box
    Given I am on the DemoQA Droppable page
    When I drag the element to the target
    Then the target box should display "Dropped!"