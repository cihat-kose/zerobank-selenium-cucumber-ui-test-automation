
  #  Test Case 3:

  #➢ ZeroBank sitesine gidiniz.
  #➢ Login olunuz
  #➢ Online Banking’e tıklayınız.
  #➢ Pay Bills’e tıklayınız.
  #➢ “Add New Payee” tıklayınız
  #➢ Name, Address, Account, Details alanlarını doldurunuz.
  #➢ Add butona tıklayınız
  #➢ İşlemin başarıyla tamamlandığını doğrulayınız.

  # NOT: 3 farklı data gönderiniz
  # NOT: Data Table kullanınız.

Feature: New Payee Functionality

  Background:
    Given Navigate to website
    And   Enter username that as "username" and password that as "password"
    When  Click login button
    Then  Verify that user logged in

  @Regression
  Scenario Outline: Fill in the required information completely
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
    Then Verify that the transaction was successful

    Examples:
      | name    | address | account | details          |
      | Hilde   | Oslo    | 156328  | Rent payment     |
      | Mustafa | Ankara  | 965832  | Amazon payment   |
      | Alex    | London  | 965832  | Electricity bill |