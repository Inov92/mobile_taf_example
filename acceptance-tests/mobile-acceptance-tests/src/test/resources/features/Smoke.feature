Feature: Smoke simple android UI tests
  User fill text field with different data

  @SeverityLevel.CRITICAL @TestCaseId("example-1")
  Scenario: User input data to text field and submit them
    Given User opens application on main screen
    When User fill textfield with "Test data" on main screen
    And press OK button on main screen
    Then Welcome text contains "Hello Test data!" on main screen