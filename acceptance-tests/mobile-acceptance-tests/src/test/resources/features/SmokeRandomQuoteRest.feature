Feature: default feature

  Scenario: default scenario
    Given Connection to Random Quote service established
    When API: send GET request to to Random Quote service
    Then response code from Random Quote service: "200"
    And  response type from Random Quote service: "success"
    And response from Random Quote service contains quote