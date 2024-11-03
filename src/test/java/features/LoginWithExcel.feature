  # Test Case 2:
  # Login with Credentials from Excel using Apache POI

  #➢ Go to the ZeroBank site.
  #➢ Enter your username and password.
  #➢ Verify that you are logged in.

  # Website to use: http://zero.webappsecurity.com/login.html
  # Username: username
  # Password: password

  # Note: Retrieve the username and password for the login process from an Excel file using Apache POI.

  Feature: Login Functionality

    @Smoke
    Scenario: Login with valid username and password

      Given Navigate to login page
      And   Enter username and password from ZeroBankData
      When  Click Sign In button
      Then  Verify that user is redirected to account summary page
      And   Verify that user icon is displayed