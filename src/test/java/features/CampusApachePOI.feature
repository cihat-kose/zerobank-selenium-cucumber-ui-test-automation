Feature: Add - Edit and Delete Functionality for Position Categories

  Scenario: Add -  Edit and Delete Position Category From Excel
    Given   Navigate to campus
    When    Enter username and password and click button
    Then    User should login successfully
    And     Navigate to position categories
    And     User add a position category with ApachePOI
    And     User edit the position category with ApachePOI
    And     User delete the position category with ApachePOI

