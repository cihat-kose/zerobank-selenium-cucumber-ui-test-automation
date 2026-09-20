  # Test Case 2:
  # Login with Credentials from Excel using Apache POI

  #➢ Go to the ZeroBank site.
  #➢ Enter your username and password.
  #➢ Verify that you are logged in.

  # Website to use: http://zero.webappsecurity.com/login.html
  # Username: username
  # Password: password

  # Note: Retrieve the username and password for the login process from an Excel file using Apache POI.

  Feature: Login Functionality with Excel Data

    @Regression @Authentication @Excel
    Scenario: Login using credentials from Excel with Apache POI

      Given Navigate to login page
      And   Retrieve and enter username and password from Excel file "ZeroBankData.xlsx"
      When  Click Sign In button
      Then  Verify that the authenticated home page is displayed
      And   Verify that user icon is displayed
