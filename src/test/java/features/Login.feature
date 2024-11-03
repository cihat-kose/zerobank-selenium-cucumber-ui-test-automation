  # Test Case 1:
  # Login Functionality

  #➢ Go to the ZeroBank site.
  #➢ Enter your username and password.
  #➢ Verify that you are logged in.

  # Website to use: http://zero.webappsecurity.com/login.html
  # Username: username
  # Password: password

  Feature: Login Functionality

    @Smoke
    Scenario: Login with valid username and password

      Given Navigate to login page
      And   Enter username that as "username" and password that as "password"
      When  Click Sign In button
      Then  Verify that user is redirected to account summary page
      And   Verify that user icon is displayed