Feature: Smoke simple android UI tests
  User fill text field with different data

  @SeverityLevel.CRITICAL @TestCaseId("example-1")
  Scenario: User input data to text field and submit them
    Given User opens application on main screen
    When User fill Text Field with "Test data" on main screen
    And User press OK button on main screen
    Then Welcome text contains "Hello Test data!" on main screen


  @SeverityLevel.CRITICAL @TestCaseId("example-2")
  Scenario: User submits empty data
    Given User opens application on main screen
    And Text Field is empty on main screen
    When  User press OK button on main screen
    Then Alert window contains "Please enter a name!"