  # Test Case 3:
  # Add New Payee with Multiple Data Sets

  #➢ Go to the ZeroBank site.
  #➢ Log in
  #➢ Click on Online Banking.
  #➢ Click on Pay Bills.
  #➢ Click "Add New Payee"
  #➢ Fill in the Name, Address, Account, Details fields.
  #➢ Click the Add button
  #➢ Verify that the transaction has been completed successfully.

  # Note 1: Please send 3 different data
  # Note 2: Use Data Table.

  Feature: Add New Payee with Data-Driven Testing

    Background:

      Given Navigate to login page
      And   Enter username that as "username" and password that as "password"
      When  Click Sign In button
      Then  Verify that the authenticated home page is displayed
      And   Verify that user icon is displayed

    @Regression @Payee
    Scenario Outline: Add a New Payee with Different Data Sets

      When Click on the element in OnlineBanking
        | onlineBanking |
        | payBills      |
        | addNewPayee   |

      And User sending name,address,account and details in OnlineBanking
        | payeeName    | <name>    |
        | payeeAddress | <address> |
        | payeeAccount | <account> |
        | payeeDetails | <details> |

      When Click to add button
      Then Verify that payee "<name>" was created

      @Smoke
      Examples: Representative payee
        | name    | address | account | details          |
        | Hilde   | Oslo    | 156328  | Rent payment     |

      Examples: Additional payee data
        | name    | address | account | details          |
        | Mustafa | Ankara  | 965832  | Amazon payment   |
        | Alex    | London  | 965832  | Electricity bill |
