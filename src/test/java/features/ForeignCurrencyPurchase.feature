  # Test Case 4:
  # Foreign Currency Functionality

  #➢ Go to the ZeroBank site.
  #➢ Log in
  #➢ Click on Online Banking.
  #➢ Click on Pay Bills.
  #➢ Click on "Purchase Foreign Currency".
  #➢ Buy from 3 different currencies.
  #➢ Verify that the transaction has been completed successfully.

  Feature: Foreign Currency Purchase Functionality

    Background:

      Given Navigate to login page
      And   Enter username that as "username" and password that as "password"
      When  Click Sign In button
      Then  Verify that the authenticated home page is displayed
      And   Verify that user icon is displayed

    @Regression @Currency
    Scenario Outline: Purchase foreign currency using a U.S. dollar amount

      When User clicks on the Online Banking section
      And  User clicks on Pay Bills
      And  User clicks on Purchase Foreign Currency
      And  User selects currency as "<currency>"
      And  User enters amount as "<amount>"
      And  User clicks on U.S. dollar checkbox
      And  User clicks on Calculate Costs button
      Then Verify that a conversion cost is displayed
      And  User clicks on Purchase
      Then Verify that the currency purchase was successful

      @Smoke
      Examples: Representative currency
        | currency | amount |
        | NOK      | 1000   |

      Examples: Additional currencies
        | currency | amount |
        | EUR      | 100    |
        | GBP      | 90     |
