Feature: default feature

  Scenario: default scenario
    Given Connection to Random Quote service established
    When User send GET request to to Random Quote service
    Then response code from Random Quote service: "200"
    And response content is not empty