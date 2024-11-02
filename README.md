
# TestingPractices_Cucumber_UI

![Cucumber](https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-FF7300?style=for-the-badge&logo=testng&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white)
![Apache POI](https://img.shields.io/badge/Apache%20POI-231F20?style=for-the-badge&logo=apache&logoColor=white)

## Table of Contents

1. [About the Project](#about-the-project)
2. [Key Benefits](#key-benefits)
3. [Features](#features)
4. [Application Under Test](#application-under-test)
5. [Reporting](#reporting)
6. [Installation](#installation)
7. [Configuration](#configuration)
8. [Usage](#usage)
9. [Parallel Testing](#parallel-testing)
10. [Folder Structure](#folder-structure)
11. [Dependencies](#dependencies)
12. [Contributing](#contributing)
13. [License](#license)
14. [Contact](#contact)

## About the Project

The **TestingPractices_Cucumber_UI** framework is an automated testing solution created to validate various user scenarios on the ZeroBank platform. Designed to integrate Cucumber for Behavior-Driven Development (BDD), Selenium for UI automation, and TestNG for test execution and reporting, this framework enables efficient and consistent testing of core features such as login, payee management, and foreign currency purchases.

Additionally, this project serves as a practical resource for learners who are interested in mastering these technologies. By providing a real-world application of Selenium, Cucumber, TestNG, and Apache POI, the framework allows users to gain hands-on experience in automated UI testing, data-driven testing, and BDD practices.

## Key Benefits

- **Automated UI Testing**: Tests core functionalities such as login, adding a new payee, and foreign currency purchase.
- **Behavior-Driven Development (BDD)**: Test scenarios are defined in a readable format using Cucumber and Gherkin syntax.
- **Cross-Browser Testing**: Tests can be run across different browsers to ensure compatibility.
- **Detailed Reporting**: Generates HTML and PDF reports with test results, including screenshots of failed scenarios.
- **Data-Driven Testing with Apache POI**: Supports parameterized testing by fetching test data from Excel files, leveraging Apache POI for seamless Excel integration. User credentials are retrieved from an Excel file for login scenarios, and test results are recorded in an Excel file for detailed tracking.
- **Page Object Model (POM)**: Simplifies test maintenance by using the POM design pattern.

## Features

- **Login Functionality**: Validates login functionality by retrieving the username and password from an Excel file using Apache POI.
- **Add New Payee**: Automates adding a new payee with multiple data inputs.
- **Purchase Foreign Currency**: Tests the currency purchase functionality with various currency types and amounts.
- **Data Table Support with Apache POI**: Uses data tables for parameterized tests, allowing multiple sets of input. Apache POI is used to manage test data from Excel files.
- **Extent Reports**: Generates HTML and PDF reports of the test results.
- **Parallel Testing**: Supports parallel execution to speed up test runs.
- **Jenkins Integration**: Easily integrate with Jenkins for continuous integration and delivery (CI/CD) processes.

## Application Under Test

The application under test is [ZeroBank](http://zero.webappsecurity.com/login.html), a demo banking application that provides various financial services. The framework tests functionalities such as login, online banking, and payee management to ensure these processes work as expected.

## Reporting

Test results are stored in the `test-output` and `testReports` directories. Reports include:

- **HTML Report**: Located at `test-output/SparkReport/Spark.html`.
- **PDF Report**: Available at `PdfReport.pdf` for a summarized overview.

Additionally, test results are recorded in an Excel file using Apache POI. Each scenario’s name, status, and browser information are written to the Excel file, providing a detailed record of the test outcomes.

## Installation

1. **Clone the repository**:
   - Open IntelliJ IDEA.
   - Go to **File > New > Project from Version Control**.
   - Enter the repository URL: `https://github.com/ChatGTHB/TestingPractices_Cucumber_UI.git`.
   - Click **Clone**.

2. **Install necessary plugins**:
   - Ensure **Cucumber for Java** and **Gherkin** plugins are installed in IntelliJ IDEA for Cucumber and Gherkin syntax support:
     - Go to **File > Settings > Plugins** (or **Preferences** on macOS).
     - Search for **Cucumber for Java** and **Gherkin**, install them, and restart IntelliJ IDEA if prompted.

3. **Open the project**:
   - IntelliJ IDEA should automatically detect and import the project as a Maven project.
   - Wait for dependencies to download.

## Configuration

- **cucumber.properties**: Configure Cucumber settings in `src/test/resources/cucumber.properties`:
   ```properties
   cucumber.publish.quiet=true
   ```
- **extent.properties**: Configure ExtentReports settings in `src/test/resources/extent.properties`:
   ```properties
   extent.reporter.spark.start=true
   extent.reporter.spark.out=test-output/SparkReport/Spark.html
   ```

## Usage

1. **Run tests**:
   - Open the `src/test/java` directory in IntelliJ IDEA.
   - Right-click on any test file or directory and select **Run 'TestName'**.

2. **Jenkins Integration**:
   - Integrate with Jenkins by creating a Jenkins job, adding a build step to run tests via Maven, and specifying `clean test` as the Maven goal.

## Parallel Testing

To run tests in parallel, you can use TestNG XML files such as `ParallelTest.xml`. Right-click on the XML file in IntelliJ and select **Run 'ParallelTest.xml'**.

## Folder Structure

```
TestingPractices_Cucumber_UI/
│
├── src/
│   ├── main/                   # Main source code
│   └── test/                   # Test source code
│       ├── java/
│       │   ├── apachePOI/      # Apache POI files and Excel data (e.g., ScenarioResults.xlsx, ZeroBankData.xlsx)
│       │   ├── features/       # Cucumber feature files
│       │   ├── pages/          # Page Object Model classes
│       │   ├── runners/        # TestNG runner classes
│       │   ├── stepDefinitions/# Cucumber step definitions
│       │   ├── utilities/      # Utility classes (e.g., DriverManager, ExcelUtility)
│       │   └── xml/            # XML configuration files for parallel testing
│       └── resources/
│           ├── cucumber.properties  # Cucumber configuration
│           └── extent.properties    # Extent report configuration
├── testReports/                # Folder for test reports
├── target/                     # Build output
└── pom.xml                     # Maven configuration file
```

## Dependencies

The dependencies for this project are defined in the `pom.xml` file:

- **Selenium**: Web browser automation
- **Cucumber**: BDD testing framework
- **TestNG**: Test execution and management
- **Apache POI**: Excel data handling, used to manage test data for data-driven testing and to log test results.
- **ExtentReports**: HTML and PDF reporting

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a Pull Request.

## License

This project is licensed under the MIT License - see the `LICENSE` file for details.

## Contact

For questions or issues, please open an issue in this repository.
