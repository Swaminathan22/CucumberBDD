Feature: Verify Player Details on ESPN Cricinfo

  Scenario: Verify player details for a certain player
    Given I launch ESPN Cricinfo website
    When I navigate to the "Players" section under "Cricket Stats"
    And I select the player named "Ashwin, R"
    Then I should see the player details and verify them against the expected data
