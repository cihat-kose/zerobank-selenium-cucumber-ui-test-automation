#Kullanılacak Web site: http://zero.webappsecurity.com/login.html
#Username: username
#Password: password

#Test Case 1: Login Functionality
#➢ ZeroBank sitesine gidiniz.
#➢ Username ve password giriniz.
#➢ Giriş yaptığınızı doğrulayın

Feature: Login Functionality

  @SmokeTest
  Scenario: Login with valid username and password
    Given Navigate to website
    And   Enter username and password from ZeroBankData
    When  Click login button
    Then  Verify that user logged in