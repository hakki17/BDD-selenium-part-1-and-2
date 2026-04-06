Feature: Geolocation

  Scenario: User can see their coordinates after clicking Where Am I
    Given I am on the Geolocation page
    When I click the "Where am I?" button
    Then I should see my latitude on the screen
    And I should see my longitude on the screen