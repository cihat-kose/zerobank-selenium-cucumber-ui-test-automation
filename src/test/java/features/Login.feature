  # Test Case 1:
  # Login Functionality

  #➢ Go to the ZeroBank site.
  #➢ Enter your username and password.
  #➢ Verify that you are logged in.

  # Website to use: http://zero.webappsecurity.com/login.html
  # Username: username
  # Password: password

  @Regression @Authentication
  Feature: Login Functionality

    @Smoke
    Scenario: Login with valid username and password

      Given Navigate to login page
      And   Enter username that as "username" and password that as "password"
      When  Click Sign In button
      Then  Verify that the authenticated home page is displayed
      And   Verify that user icon is displayed

    @Negative
    Scenario Outline: Reject invalid credentials
      Given Navigate to login page
      And Enter username that as "<username>" and password that as "<password>"
      When Submit invalid login credentials
      Then Verify that login is rejected

      Examples:
        | username     | password       |
        | username     | wrong-password |
        | unknown-user | password       |
