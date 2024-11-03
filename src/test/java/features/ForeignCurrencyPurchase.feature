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
      Then  Verify that user is redirected to account summary page
      And   Verify that user icon is displayed

    @Regression
    Scenario Outline: Purchase foreign currency and convert to U.S. dollars

      When User clicks on the Online Banking section
      And  User clicks on Pay Bills
      And  User clicks on Purchase Foreign Currency
      And  User selects currency as "<currency>"
      And  User enters amount as "<amount>"
      And  User clicks on U.S. dollar checkbox
      And  User clicks on Calculate Costs button
      And  User clicks on Purchase
      Then Verify that the transaction was successful

      Examples:
        | currency | amount |
        | NOK      | 1000   |
        | EUR      | 100    |
        | GBP      | 90     |