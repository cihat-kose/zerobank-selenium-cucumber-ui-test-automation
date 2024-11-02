
  # Test Case 1:
  # Login Functionality

  #➢ ZeroBank sitesine gidiniz.
  #➢ Username ve password giriniz.
  #➢ Giriş yaptığınızı doğrulayınız.

  # Kullanılacak Web site: http://zero.webappsecurity.com/login.html
  # Username: username
  # Password: password

  # NOT: Giriş işlemi için kullanıcı adı ve şifreyi Apache POI kullanarak bir Excel dosyasından alınız.

Feature: Login Functionality

  @Smoke
  Scenario: Login with valid username and password

    Given Navigate to website
    And   Enter username and password from ZeroBankData
    When  Click login button
    Then  Verify that user logged in