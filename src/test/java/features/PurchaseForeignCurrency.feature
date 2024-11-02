
  # Test Case 2:
  # Foreign Currency Functionality

  #➢ ZeroBank sitesine gidiniz.
  #➢ Login olunuz
  #➢ Online Banking’e tıklayınız.
  #➢ Pay Bills’e tıklayınız.
  #➢ “Purchase Foreign Currency” tıklayınız.
  #➢ 3 farklı döviz türünden satın alınız.
  #➢ İşlemin başarıyla tamamlandığını doğrulayınız.

Feature: Purchase Foreign Currency Functionality

  Background:
    Given Navigate to website
    And   Enter username that as "username" and password that as "password"
    When  Click login button
    Then  Verify that user logged in

  @Regression
  Scenario Outline: Convert amount to dollars
    When Click to Online Banking
    And  Click to Pay Bills
    And  Click to Purchase Foreign Currency
    And  Select currency that as "<value>"
    And  Enter to amount that as "<amount>"
    And  Click to U.S. dollar checkbox
    And  Click to Calculate Costs button
    And  Click to Purchase
    Then Verify that the transaction was successful

    Examples:
      | value | amount |
      | NOK   | 1000   |
      | EUR   | 100    |
      | GBP   | 90     |